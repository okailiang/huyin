package press.wein.home.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import press.wein.home.exception.ServiceException;
import press.wein.home.model.vo.CityVo;
import press.wein.home.util.PasswordUtil;
import press.wein.home.util.ResponseUtils;


/**
 * 公用的url
 *
 * @author oukailiang
 * @create 2017-07-22 下午10:34
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController extends BaseController {
    public static final Logger LOG = LoggerFactory.getLogger(CommonController.class);

    /**
     * 获得城市详细信息
     *
     * @return
     */
    @RequestMapping(value = "/getPassword", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<Object> getPassword() throws ServiceException {
        String result;
        try {
            result = PasswordUtil.getRandomPassword();
        } catch (Exception e) {
            LOG.error("CommonController.getPassword Exception", e);
            return ResponseUtils.error();
        }
        return ResponseUtils.success("password", result);
    }
}
