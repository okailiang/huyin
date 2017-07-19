package press.wein.home.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import press.wein.home.common.ApplicationUserContext;
import press.wein.home.common.Page;
import press.wein.home.constant.TipConstants;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.bo.UserSession;
import press.wein.home.model.vo.CityVo;
import press.wein.home.model.vo.ProvinceVo;
import press.wein.home.service.CityService;
import press.wein.home.util.CommonUtil;
import press.wein.home.util.ResponseUtils;
import press.wein.home.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author oukailiang
 * @create 2017-07-17 上午10:36
 */
@Controller
@RequestMapping(value = "/common/city")
public class CityController extends BaseController {
    public static final Logger LOG = LoggerFactory.getLogger(CityController.class);

    @Autowired
    private CityService cityService;

    /**
     * 更新
     *
     * @param cityVo
     * @param request
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> updateCity(@RequestBody CityVo cityVo, HttpServletRequest request) throws ServiceException {

        try {
            this.setModifier(cityVo);
            cityService.updateCity(cityVo);
        } catch (ServiceException e) {
            LOG.error("CityController.updateCity ServiceException inputParam = [{}]", cityVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("CityController.updateCity Exception inputParam = [{}]", cityVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(TipConstants.UPDATE_SUCCESS);
    }
    /**
     * 分页获取城市列表
     *
     * @return
     */
    @RequestMapping(value = "/listCitysWithPage", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> listCitysWithPage(Page<CityVo> page, CityVo cityVo) throws ServiceException {
        Page<CityVo> cityVoPage;
        try {
            this.handleCityVo(cityVo);
            cityVoPage = cityService.listCitysWithPage(page, cityVo);
        } catch (ServiceException e) {
            LOG.error("CityController.listCitysWithPage ServiceException inputParam = [{}]", cityVo.toString(), e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("CityController.listCitysWithPage Exception inputParam = [{}]", cityVo.toString(), e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(cityVoPage);
    }

    /**
     * 处理名称和拼音
     * @param cityVo
     */
    private void handleCityVo(CityVo cityVo){
        if(StringUtil.isNotBlank(cityVo.getName())){
            if(CommonUtil.isLetter(cityVo.getName())){
                cityVo.setPinyin(cityVo.getName().toLowerCase());
                cityVo.setName(null);
            }
        }
    }
    /**
     * 获取省列表
     *
     * @return
     */
    @RequestMapping(value = "/listProvince", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> listProvince() throws ServiceException {
        List cityVoPage;
        try {
            cityVoPage = cityService.listProvinces();
        } catch (ServiceException e) {
            LOG.error("CityController.listCitysWithPage ServiceException", e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("CityController.listCitysWithPage Exception ", e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(cityVoPage);
    }

    /**
     * 根据parentId获取城市列表
     *
     * @return
     */
    @RequestMapping(value = "/listCitysByParentId", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> listCitysByParentId(@RequestParam(value = "parentId") Integer parentId) throws ServiceException {
        List<CityVo> cityVoList;
        try {
            cityVoList = cityService.listCitysByParentId(parentId);
        } catch (ServiceException e) {
            LOG.error("CityController.listCitysByParentId ServiceException inputParam = [parentId:{}]", parentId, e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("CityController.listCitysByParentId Exception inputParam = [parentId:{}]", parentId, e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(cityVoList);
    }

    /**
     * 获得城市详细信息
     *
     * @return
     */
    @RequestMapping(value = "/getCityById", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> getCityById(@RequestParam(value = "cityId") Integer cityId) throws ServiceException {
        CityVo cityVo;
        try {
            cityVo = cityService.getCityById(cityId);
        } catch (ServiceException e) {
            LOG.error("CityController.getCityById ServiceException inputParam = [{}]",cityId, e);
            return ResponseUtils.error(e.getMessage());
        } catch (Exception e) {
            LOG.error("CityController.getCityById Exception inputParam = [{}]", cityId, e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success(cityVo);
    }

    private void setModifier(CityVo vo) {
        UserSession userSession = ApplicationUserContext.getUser();
        vo.setModifier(userSession.getName());
        vo.setModifierId(userSession.getId());
        vo.setModifyTime(new Date());
    }
}
