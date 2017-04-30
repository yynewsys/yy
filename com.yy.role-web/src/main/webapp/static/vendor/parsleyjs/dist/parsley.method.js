
    window.Parsley.addValidator('checkidcard', {
        validateString: function (value) {
            return checkIdcard(value);
        }
    }).addValidator('mobilephone', {
        validateString: function (value) {
            return checkMobilePhone(value);
        }
    }).addValidator('twonum', {
        validateString: function (value) {
            return twoNum(value);
        }
    });

    Parsley.addMessages('zh-cn', {
        defaultMessage: "不正确的值",
        type: {
            email: "请输入一个有效的电子邮箱地址",
            url: "请输入一个有效的链接",
            number: "请输入正确的数字",
            integer: "请输入正确的整数",
            digits: "请输入正确的号码",
            alphanum: "请输入字母或数字"
        },
        notblank: "请输入值",
        required: "必填项",
        pattern: "格式不正确",
        min: "输入值请大于或等于 %s",
        max: "输入值请小于或等于 %s",
        range: "输入值应该在 %s 到 %s 之间",
        minlength: "请输入至少 %s 个字符",
        maxlength: "请输入至多 %s 个字符",
        length: "字符长度应该在 %s 到 %s 之间",
        mincheck: "请至少选择 %s 个选项",
        maxcheck: "请选择不超过 %s 个选项",
        check: "请选择 %s 到 %s 个选项",
        equalto: "输入值不同",
        checkidcard: "身份证号码有误",
        mobilephone: "请输入正确的手机号码",
        twonum:"请输入正确的数字"
    });
    Parsley.setLocale('zh-cn');
    function checkIdcard(num) {
        num = num.toUpperCase();
//身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
        if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
//alert('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。');
            return false;
        }
//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
//下面分别分析出生日期和校验位
        var len, re;
        len = num.length;
        if (len == 15) {
            re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
            var arrSplit = num.match(re);

//检查生日日期是否正确
            var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
            var bGoodDay;
            bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
            if (!bGoodDay) {
//alert('输入的身份证号里出生日期不对！');
                return false;
            }
            else {
//将15位身份证转成18位
//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                var nTemp = 0, i;
                num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
                for (i = 0; i < 17; i++) {
                    nTemp += num.substr(i, 1) * arrInt[i];
                }
                num += arrCh[nTemp % 11];
                return true;
            }
        }
        if (len == 18) {
            re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
            var arrSplit = num.match(re);

//检查生日日期是否正确
            var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
            var bGoodDay;
            bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
            if (!bGoodDay) {
//alert(dtmBirth.getYear());
//alert(arrSplit[2]);
//alert('输入的身份证号里出生日期不对！');
                return false;
            }
            else {
//检验18位身份证的校验码是否正确。
//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
                var valnum;
                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                var nTemp = 0, i;
                for (i = 0; i < 17; i++) {
                    nTemp += num.substr(i, 1) * arrInt[i];
                }
                valnum = arrCh[nTemp % 11];
                if (valnum != num.substr(17, 1)) {
//alert('18位身份证的校验码不正确！应该为：' + valnum);
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    /*验证手机号码*/
    function checkMobilePhone(MobilePhone){
        var regexMobilePhone = /^1[3|4|5|7|8][0-9]\d{8}$/;
        if(!regexMobilePhone.test(MobilePhone)){
            return false;
        }
        return true;
    }
    /*验证保留2位小数的正数*/
    function twoNum(number){
        var regexNumber = /^\d+(.\d{1,4})?$/;
        if(!regexNumber.test(number)){
            return false;
        }
        return true;
    }
