// 百度地图API功能
function G(id) {
	return document.getElementById(id);
}	

//创建和初始化地图函数：
function initMap(){		
    window.map = new BMap.Map("l-map", { //设置等级范围
		minZoom : 4,
		maxZoom : 18
	});
    map.centerAndZoom("北京",12);
    map.enableScrollWheelZoom(true);
    map.addControl(new BMap.NavigationControl());
} 

//重置返回所有结果
window.reset = function(){
	//s:{''只返回找到的结果|all返回所有的} 
    var dd = searchClass.search({k:"title",d:"显示全部",t:"single",s:"all"});
    addMarker(dd);
}

//创建marker
window.addMarker = function (data){
    map.clearOverlays();
    for(var i=0;i<data.length;i++){
        var json = data[i];
        var p0 = json.point.split("|")[0];
        var p1 = json.point.split("|")[1];
        var point = new BMap.Point(p0,p1);
        var marker = new BMap.Marker(point);
        marker.enableMassClear();
        var iw = createInfoWindow(i);
        var label = new BMap.Label(json.title,{"offset":new BMap.Size(22, 22)});
        marker.setLabel(label);
        map.addOverlay(marker);
        label.setStyle({
                    borderColor:"#808080",
                    color:"#333",
                    cursor:"pointer"
        });
        (function(){
            var _json = json;
            var _iw = createInfoWindow(_json);
            var _marker = marker;
             _marker.addEventListener("click",function(){
            	 $("#printer-name").select2().val(_json.id).trigger("change");
            	 this.openInfoWindow(_iw);
        });
        _iw.addEventListener("open",function(){
        _marker.getLabel().hide();
        })
        _iw.addEventListener("close",function(){
        _marker.getLabel().show();
        })
        label.addEventListener("click",function(){
        _marker.openInfoWindow(_iw);
        })
        if(!!json.isOpen){
                label.hide();
          _marker.openInfoWindow(_iw);
        }
        })()
    }
}



//创建InfoWindow
function createInfoWindow(json){
   var iw = new BMap.InfoWindow(
		    '<div  style="margin:0;line-height:20px;padding:4px;width:360px;">'
		    +'<h4>'+json.title+'</h4>'
			+ '<img src='+json.icon+' alt="" style="float:right;zoom:1;overflow:hidden;width:120px;height:120px;margin-left:3px;"/>'
			+ '地址：'+json.content+'<br/>简介：'+json.title+'<br/>电话：'+json.teleNumber
			+ '</div>');
    return iw;
}


function SearchClass(data){
    this.datas = data;
}

//查找
SearchClass.prototype.search = function(rule){
    if(this.datas == null){alert("数据不存在!");return false;}
    if(this.trim(rule) == "" || this.trim(rule.d) == "" || this.trim(rule.k) == "" || this.trim(rule.t) == ""){
    	alert("请指定要搜索内容!");return false;
    }
    var reval = [];
    var datas = this.datas;
    var len = datas.length;
    var me = this;
    var ruleReg = new RegExp(this.trim(rule.d));
    var hasOpen = false;
    var addData = function(data,isOpen){
        // 第一条数据打开信息窗口
        if(isOpen && !hasOpen){
            hasOpen = true;
            data.isOpen = 1;
        }else{
            data.isOpen = 0;
        }
        reval.push(data);
    };
    var getData = function(data,key){
        var ks = me.trim(key).split(/\./);
        var i = null,s = "data";
        if(ks.length == 0){
            return data;
        }else{
            for(var i = 0; i < ks.length; i++){
                s += '["' + ks[i] + '"]';
            }
            return eval(s);
        }
    };
    for(var cnt = 0; cnt < len; cnt++){
        var data = datas[cnt];
        var d = getData(data,rule.k);
        if(rule.t == "single" && rule.d == d){
            addData(data,true);
        }else if(rule.t != "single" && ruleReg.test(d)){
            addData(data,true);
        }else if(rule.s == "all"){
            addData(data,false);
        }
    }
    return reval;
};

SearchClass.prototype.setData = function(data){
    this.datas = data;
};

SearchClass.prototype.trim = function(str){
 	if(str == null){
 		str = "";}
 	else{ 
 		str = str.toString();
 	}
    return str.replace(/(^[\s\t\xa0\u3000]+)|([\u3000\xa0\s\t]+$)/g, "");
};

//创建和初始化地图
initMap();

//请求数据返回打印店地址
function getBaseData(address) {
	cityId=$("#printer-city").val();
	cityName=$("#printer-city option:selected").text();	
	var arr=new Array();
	for(var i=0;i<address.length;i++){
		var BASEDATA = new Object();
		BASEDATA.id=address[i].id;
		BASEDATA.title= address[i].printShopName;
	    BASEDATA.content =address[i].address;
		BASEDATA.teleNumber=address[i].teleNumber;
		BASEDATA.point= address[i].longitude+"|"+address[i].latitude;
		BASEDATA.isOpen=0;
		BASEDATA.icon= address[i].printShopImage;
		arr[i]=BASEDATA;
	}
	//创建自定义搜索类
	map.centerAndZoom(cityName,12);
	window.searchClass = new SearchClass();    
	searchClass.setData(arr);
	reset();
}

//选中的点跳动
function printerSelected(printShop,id){
	var os = map.getOverlays();
	for(var i = 0; i < os.length ; i++){
	     if(os[i].toString()=="[object Marker]") {
	    	 for(var i=0;i<printShop.length;i++){
	    			if(printShop[i].id==id){
	    				os[i].setAnimation(BMAP_ANIMATION_BOUNCE);
	    				map.addOverlay(os[i]);
	    			}else{
	    				os[i].setAnimation(null);
	    				map.addOverlay(os[i]);
	    			}
	    		}
	     }
	}	
}