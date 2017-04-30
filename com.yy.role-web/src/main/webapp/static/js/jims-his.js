/*!
 * Copyright &copy; 2012-2014 <a href="https://github.com.jims.emr">JeeSite</a> All rights reserved.
 * 
 * 通用公共方法
 * @author ThinkGem
 * @version 2014-4-29
 */
$(document).ready(function () {
    try {
        // 链接去掉虚框
        $("a").bind("focus", function () {
            if (this.blur) {
                this.blur()
            }
            ;
        });
        //所有下拉框使用select2
        $("select").select2();
    } catch (e) {
        // blank
    }
});

/**
 * divLoad
 * @param divId
 * @param url
 */
function loadDiv(divId, url) {
    url=timestamp(url);
    $("#" + divId).load(url, '', function () {
        flag = true;
        $("select").select2({matcher: function (term, text) {
            term = term.toUpperCase();
            if (makePy(text)[0].indexOf(term) != -1 || text.indexOf(term) != -1) {
                return text;
            }
            return "";
        }
        });
        $(".datatable th input[type='checkbox']").click(function () {
            var table = $(this).parent().parent().parent().parent();
            var ck = table.attr("checkbox");
            if (ck == "true") {
                if($(this).is(':checked')){
                    $(this).prop("checked",true);
                    table.find("td input[type='checkbox']").each(function(){
                        $(this).prop("checked", true);
                        $(this).parent().parent().addClass("table-selected");
                    })
                }else{
                    $(this).prop("checked",false);
                    table.find("tr input[type='checkbox']").each(function(){
                        $(this).prop("checked", false);
                        $(this).parent().parent().removeClass("table-selected");
                    })
                }
            }
        })
        $(".datatable tbody tr").click(function () {
            var ck = $(this).parent().parent().attr("checkbox");
            if (ck == "true") {
                var table_select = $(this).attr("class");
                if (table_select == 'table-selected') {
                    $(this).removeClass("table-selected")
                    $(this).find("td:first input[type='checkbox']").eq(0).prop("checked", false);
                } else {
                    $(this).addClass("table-selected");
                    $(this).find("td:first input[type='checkbox']").eq(0).prop("checked", true);
                }

            } else {
                $(this).addClass("table-selected").siblings().removeClass("table-selected");
                $($(this).parent().find("tr")).each(function (i) {
                    $(this).find("td:first input[type='checkbox']").eq(0).prop("checked", false);
                })
                $(this).find("td:first input[type='checkbox']").eq(0).prop("checked", true);
            }
        })

    });
}
/**
 *form表单提交load
 * @param divId
 * @param formId
 * @param url
 */
function loadDivForm(divId, formId, url) {
    url = url + '?' + $("#" + formId).serialize();
    setTimeout(loadDiv(divId, url), 300);
    return false;
}
/**
 *form保存跳转
 * @param divId
 * @param formId
 * @param url
 */
function formSaveLoad(divId, formId, url, hrefUrl) {
    if ($('#' + formId).parsley().validate()) {
        setTimeout(formSaveMethod(divId, formId, url, hrefUrl), 300);
    }
    return false;
}
function formSaveMethod(divId, formId, url, hrefUrl) {
    url=timestamp(url);
    hrefUrl=timestamp(hrefUrl);
    if (flag) {
        flag = false;
        formSubmitInput(formId);
        $("#" + formId + " input,#" + formId + " select").removeAttr("disabled");
        $("#" + formId).ajaxSubmit({
            type: 'post', // 提交方式 get/post
            url:url, // 需要提交的 url
            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
                if (data.code == "success") {
                    toastr.success(data.data);
                    loadDiv(divId, hrefUrl);
                } else if(data.code == 'info'){
                    loadDiv(divId, hrefUrl);
                } else {
                    flag = true;
                    toastr.error(data.data);
                }
            }
        });
    }
    return false;
}
/**
 *删除跳转
 * @param divId
 * @param formId
 * @param url
 * @param hrefUrl
 */
function delLoad(divId, url, hrefUrl) {
    url=timestamp(url);
    hrefUrl=timestamp(hrefUrl);
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        success: function (data) {
            if (data.code == "success") {
                toastr.success(data.data);
                loadDiv(divId, hrefUrl);
            } else if (data.code == "info") {
                toastr.info(data.data);
            } else {
                toastr.error(data.data);
            }
        },
        error: function (data) {
            toastr.error('网络连接错误,请检查网络');
        }
    });
}
function confirmExtend(str, med) {
    layer.confirm(str, {
        skin: 'layui-layer-molv',
        btn: ['确定', '取消'] //按钮
    }, function (index) {
        med();
        layer.close(index);
    }, function (index) {
        layer.close(index);
    });
}

/**
 * 验证用户名唯一性
 * @param type
 * @param inputId
 */
function validPersonInfoUnique(type, inputId, personId,url) {
    var inputValue = $("#" + inputId).val();
    var idValue = $("#" + personId).val();
    if (inputValue != null && inputValue.trim() != '') {
        $.ajax({
            type: "POST",
            url: url+"?personId=" + idValue + "&type=" + type + "&value=" + inputValue,
            dataType: "json",
            success: function (data) {
                if (data.code == "success") {
                    flag = true;
//                            toastr.success(data.data);
                    return true;
                } else if (data.code == 'pass') {
                    flag = true;
                    return true;
                } else if (data.code == 'error') {
                    $("#" + inputId).val('');
                    toastr.error(data.data);
                    flag = false;
                    return false;
                }
            }, error: function (data) {
                toastr.error('网络连接错误,请检查网络');
            }
        });
    }

}
/**
 * 验证旧密码正确性
 * @param password
 */
function validOldPass(password,url) {
    var inputValue = $("#" + password).val();
    if (inputValue != null && inputValue.trim() != '') {
        $.ajax({
            type: "POST",
            url: url+"?password=" + inputValue,
            dataType: "json",
            success: function (data) {
                if (data.code == "success") {
                    return true;
                } else if (data.code == 'error') {
                    $("#" + password).val('');
                    toastr.error(data.data);
                    return false;
                }
            }, error: function (data) {
                toastr.error('网络连接错误,请检查网络');
            }
        });
    }

}


function autoComplete(autoInput, url, parseMethod, showMethod, resultMethod) {
    if (url != null && url != '') {
        $("#" + autoInput).autocomplete(url, {
            minChars: 0, //在触发autoComplete前用户至少需要输入的字符数.
            max: 20,//autoComplete下拉显示项目的个数
            autoFill: false, //要不要在用户选择时自动将用户当前鼠标所在的值填入到input框
            //mustMatch: true, //autoComplete只会允许匹配的结果出现在输入框,所有当用户输入的是非法字符时将会得不到下拉框
            matchContains: true, //决定比较时是否要在字符串内部查看匹配,如ba是否与foo bar中的ba匹配.使用缓存时比较重要.不要和autofill混用
            multipleSeparator: '',//如果是多选时,用来分开各个选择的字符.
            multiple: false, //是否允许输入多个值即多次使用autoComplete以输入多个值
            scrollHeight: 300,
            width: "20%",
            dataType: 'json',
            method: 'POST',
            parse: function (data) {
                return parseMethod(data);
            }, formatItem: function (data, i, max) {//格式化列表中的条目 row:条目对象,i:当前条目数,max:总条目数
                return showMethod(data, i, max);
            }
        }).result(function (event, data, formatted) {
            resultMethod(event, data, formatted);

        });
    }
}

// 引入js和css文件
function include(id, path, file) {
    if (document.getElementById(id) == null) {
        var files = typeof file == "string" ? [file] : file;
        for (var i = 0; i < files.length; i++) {
            var name = files[i].replace(/^\s|\s$/g, "");
            var att = name.split('.');
            var ext = att[att.length - 1].toLowerCase();
            var isCSS = ext == "css";
            var tag = isCSS ? "link" : "script";
            var attr = isCSS ? " type='text/css' rel='stylesheet' " : " type='text/javascript' ";
            var link = (isCSS ? "href" : "src") + "='" + path + name + "'";
            document.write("<" + tag + (i == 0 ? " id=" + id : "") + attr + link + "></" + tag + ">");
        }
    }
}

// 获取URL地址参数
function getQueryString(name, url) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    if (!url || url == "") {
        url = window.location.search;
    } else {
        url = url.substring(url.indexOf("?"));
    }
    r = url.substr(1).match(reg)
    if (r != null) return unescape(r[2]);
    return null;
}

//获取字典标签
function getDictLabel(data, value, defaultValue) {
    for (var i = 0; i < data.length; i++) {
        var row = data[i];
        if (row.value == value) {
            return row.label;
        }
    }
    return defaultValue;
}

// 打开一个窗体
function windowOpen(url, name, width, height) {
    var top = parseInt((window.screen.height - height) / 2, 10), left = parseInt((window.screen.width - width) / 2, 10),
        options = "location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes," +
            "resizable=yes,scrollbars=yes," + "width=" + width + ",height=" + height + ",top=" + top + ",left=" + left;
    window.open(url, name, options);
}

// 恢复提示框显示
function resetTip() {
    top.$.jBox.tip.mess = null;
}

// 关闭提示框
function closeTip() {
    top.$.jBox.closeTip();
}

//显示提示框
function showTip(mess, type, timeout, lazytime) {
    resetTip();
    setTimeout(function () {
        top.$.jBox.tip(mess, (type == undefined || type == '' ? 'info' : type), {opacity: 0,
            timeout: timeout == undefined ? 2000 : timeout});
    }, lazytime == undefined ? 500 : lazytime);
}

// 显示加载框
function loading(mess) {
    if (mess == undefined || mess == "") {
        mess = "正在提交，请稍等...";
    }
    resetTip();
    top.$.jBox.tip(mess, 'loading', {opacity: 0});
}

// 警告对话框
function alertx(mess, closed) {
    top.$.jBox.info(mess, '提示', {closed: function () {
        if (typeof closed == 'function') {
            closed();
        }
    }});
    top.$('.jbox-body .jbox-icon').css('top', '55px');
}

// 确认对话框
function confirmx(mess, href, closed) {
    top.$.jBox.confirm(mess, '系统提示', function (v, h, f) {
        if (v == 'ok') {
            if (typeof href == 'function') {
                href();
            } else {
                resetTip(); //loading();
                location = href;
            }
        }
    }, {buttonsFocus: 1, closed: function () {
        if (typeof closed == 'function') {
            closed();
        }
    }});
    top.$('.jbox-body .jbox-icon').css('top', '55px');
    return false;
}

// 提示输入对话框
function promptx(title, lable, href, closed) {
    top.$.jBox("<div class='form-search' style='padding:20px;text-align:center;'>" + lable + "：<input type='text' id='txt' name='txt'/></div>", {
        title: title, submit: function (v, h, f) {
            if (f.txt == '') {
                top.$.jBox.tip("请输入" + lable + "。", 'error');
                return false;
            }
            if (typeof href == 'function') {
                href();
            } else {
                resetTip(); //loading();
                location = href + encodeURIComponent(f.txt);
            }
        }, closed: function () {
            if (typeof closed == 'function') {
                closed();
            }
        }});
    return false;
}

// 添加TAB页面
function addTabPage(title, url, closeable, $this, refresh) {
    top.$.fn.jerichoTab.addTab({
        tabFirer: $this,
        title: title,
        closeable: closeable == undefined,
        data: {
            dataType: 'iframe',
            dataLink: url
        }
    }).loadData(refresh != undefined);
}

// cookie操作
function cookie(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
        }
        var path = options.path ? '; path=' + options.path : '';
        var domain = options.domain ? '; domain=' + options.domain : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
}

// 数值前补零
function pad(num, n) {
    var len = num.toString().length;
    while (len < n) {
        num = "0" + num;
        len++;
    }
    return num;
}

// 转换为日期
function strToDate(date) {
    return new Date(date.replace(/-/g, "/"));
}

// 日期加减
function addDate(date, dadd) {
    date = date.valueOf();
    date = date + dadd * 24 * 60 * 60 * 1000;
    return new Date(date);
}

//截取字符串，区别汉字和英文
function abbr(name, maxLength) {
    if (!maxLength) {
        maxLength = 20;
    }
    if (name == null || name.length < 1) {
        return "";
    }
    var w = 0;//字符串长度，一个汉字长度为2
    var s = 0;//汉字个数
    var p = false;//判断字符串当前循环的前一个字符是否为汉字
    var b = false;//判断字符串当前循环的字符是否为汉字
    var nameSub;
    for (var i = 0; i < name.length; i++) {
        if (i > 1 && b == false) {
            p = false;
        }
        if (i > 1 && b == true) {
            p = true;
        }
        var c = name.charCodeAt(i);
        //单字节加1
        if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
            w++;
            b = false;
        } else {
            w += 2;
            s++;
            b = true;
        }
        if (w > maxLength && i <= name.length - 1) {
            if (b == true && p == true) {
                nameSub = name.substring(0, i - 2) + "...";
            }
            if (b == false && p == false) {
                nameSub = name.substring(0, i - 3) + "...";
            }
            if (b == true && p == false) {
                nameSub = name.substring(0, i - 2) + "...";
            }
            if (p == true) {
                nameSub = name.substring(0, i - 2) + "...";
            }
            break;
        }
    }
    if (w <= maxLength) {
        return name;
    }
    return nameSub;
}
/**
 * table列显示隐藏
 * @param tableId
 * @param columns table列索引 例： 0,1，2,3
 * @param type 显示隐藏列 1.显示table列 2.隐藏table列
 */
function hideShowTableTd(tableId, columns, type) {
    var strs = new Array(); //定义一数组
    strs = columns.split(","); //字符分割
    var tableTd = "";
    for (var i = 0; i < strs.length; i++) {
        tableTd += "td:eq(" + strs[i] + "),th:eq(" + strs[i] + "),"
    }
    tableTd = tableTd.substring(0, tableTd.length - 1);
    if (type == '1') {
        $('#' + tableId + ' tr').find(tableTd).show();
    }
    if (type == '2') {
        $('#' + tableId + ' tr').find(tableTd).hide();
    }
}
/**
 * 获取表单内容转为数组
 * @returns {{}}
 */
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//表单自动回填的方法
var fillForm = function ($form, json) {
    var jsonObj = json;
    if (typeof json === 'string') {
        jsonObj = $.parseJSON(json);
    }
    for (var key in jsonObj) { // 遍历json字符串
        var objtype = jsonObjType(jsonObj[key]); // 获取值类型
        if (objtype === "array") { // 如果是数组，一般都是数据库中多对多关系
            var obj1 = jsonObj[key];
            for (var arraykey in obj1) {
                // alert(arraykey + jsonObj[arraykey]);
                var arrayobj = obj1[arraykey];
                for (var smallkey in arrayobj) {
                    setCkb(key, arrayobj[smallkey]);
                    break;
                }
            }
        } else if (objtype === "object") { // 如果是对象，啥都不错，大多数情况下，会有 xxxId
            // 这样的字段作为外键表的id

        } else if (objtype === "string") { // 如果是字符串
            var str = jsonObj[key];
            var date = new Date(str);
            if (date.getDay()) { // 这种判断日期是本人懒，不想写代码了，大家慎用。
                $("[name=" + key + "]", $form).val(str);
                continue;
            }

            var tagobjs = $("[name=" + key + "]", $form);
            if ($(tagobjs[0]).attr("type") == "radio") {// 如果是radio控件
                $.each(tagobjs, function (keyobj, value) {
                    if ($(value).attr("val") == jsonObj[key]) {
                        value.checked = true;
                    }
                });
                continue;
            }
            $("[name=" + key + "]", $form).val(jsonObj[key]);
        } else { // 其他的直接赋值
            $("[name=" + key + "]", $form).val(jsonObj[key]);
        }

    }
}

var setCkb = function (name, value) {
    $("[name=" + name + "][val=" + value + "]").attr("checked", "checked");
}

var fillckb = function (name, json) {
    var jsonObj = json;
    if (typeof json === 'string') {
        jsonObj = $.parseJSON(json);
    }
    var str = jsonObj[name];
    if (typeof str === "string") {
        var array = str.split(",");
        $.each(array, function (key, value) {
            setCkb(name, value);
        });
    }
}

var jsonObjType = function (obj) {
    if (typeof obj === "object") {
        var teststr = JSON.stringify(obj);
        if (teststr[0] == '{' && teststr[teststr.length - 1] == '}')
            return "class";
        if (teststr[0] == '[' && teststr[teststr.length - 1] == ']')
            return "array";
    }
    return typeof obj;
}

//保存form之前操作
function formSubmitInput(fromId) {
    $("#" + fromId + " div").each(function () {
        var inputId = $(this).attr("submit_id");
        if (typeof(inputId) != "undefined") {
            var html = $(this).html();
            $("#" + inputId).val(escape(html));
        }
    })
}
//查看form 中div
function getFormDiv(fromId) {
    $("#" + fromId + " div").each(function () {
        var inputId = $(this).attr("submit_id");
        if (typeof(inputId) != "undefined") {
            $(this).html(unescape($("#" + inputId).val()));
        }
    })
}
//解决浏览器缓存
function timestamp(url){
    //  var getTimestamp=Math.random();
    var getTimestamp=new Date().getTime();
    if(url.indexOf("?")>-1){
        url=url+"&timestamp="+getTimestamp
    }else{
        url=url+"?timestamp="+getTimestamp
    }
    return url;
}
/**
 * 通过身份证获取年龄
 * @param idNoId  身份证 输入框id
 * @param ageId   年龄  输入框id
 * @returns {boolean}
 */
function getBaseinfo(idNoId,ageId) {
    var idNo = $("#"+idNoId).val();
    if(idNo !="" && idNo != null ){
        var date = new Date();
        var year = date.getFullYear();
        var birthday_year = parseInt(idNo.substr(6, 4));
        var userage = year - birthday_year;

        $('#'+ageId).val(userage);
    }
}

/**
 * 通过身份证获取生日
 * @param idNoId
 * @param birthdayId
 */
function getBirthday(idNoId,birthdayId){
    var birthday = "";
    var idNo = $('#'+idNoId).val();
    if (idNo != null && idNo != "") {
        if (idNo.length == 15) {
            birthday = "19" + idNo.substr(6, 6);
        } else if (idNo.length == 18) {
            birthday = idNo.substr(6, 8);
        }
        birthday = birthday.replace(/(.{4})(.{2})/, "$1-$2-");
        $("#"+birthdayId).val(birthday);
    }
}

function loadDoctorByDept(deptId, doctorId,inputId,url) {
    $.ajax({
        type: "POST",
        url: url+'?deptId=' + deptId,
        dataType: "json",
        success: function (data) {
            if (data.length > 0) {
                $("#"+inputId).find("option").remove();
                for (var i = 0; i < data.length; i++) {
                    var user = data[i];
                    if (doctorId == user.id) {
                        $("#"+inputId).append('<option selected="selected" value="' + user.id + '">' + user.name + '</option>');
                    } else {
                        $("#"+inputId).append('<option value="' + user.id + '">' + user.name + '</option>');
                    }
                }
            } else {
                $("#"+inputId).find("option").remove();
                $("#"+inputId).append('<option value="">请选择</option>');
            }
        },
        error: function (data) {
            toastr.error('网络连接错误,请检查网络');
        }
    });
}