//需要改为你的服务器网址
var base_url = '//localhost:8091';

(function ($) {
    // 封装 get 与 post
    $([{name: 'cget', method: 'get'}, {name: 'cpost', method: 'post'}]).each(function (index, item) {
        $[item.name] = function (url, data, callback) {
            // 参数移位
            if (!data) {
                data = callback;
                callback = undefined;
            }
            $[item.method](url, data, function (data) {
                // 2 未登录
                if(data.state === 2) {
                    flushStatus();
                    return swal('未登录, 请先登录');
                }
                if (!data.state) {
                    return swal(data.msg);
                }
                if (typeof callback == 'function') {
                    callback(data.obj);
                }
            });
        }
    });

    // PRE LOADER
    $(window).load(function () {
        $('.preloader').fadeOut(1000); // set duration in brackets
    });

    // MENU
    $('.navbar-collapse a').on('click', function () {
        $(".navbar-collapse").collapse('hide');
    });

    $(window).scroll(function () {
        if ($(".navbar").offset().top > 50) {
            $(".navbar-fixed-top").addClass("top-nav-collapse");
        } else {
            $(".navbar-fixed-top").removeClass("top-nav-collapse");
        }
    });

    // PARALLAX EFFECT
    $.stellar({
        horizontalScrolling: false
    });

    // MAGNIFIC POPUP
    $('.image-popup').magnificPopup({
        type: 'image',
        removalDelay: 300,
        mainClass: 'mfp-with-zoom',
        gallery: {
            enabled: true
        },
        zoom: {
            enabled: true, // By default it's false, so don't forget to enable it
            duration: 300, // duration of the effect, in milliseconds
            easing: 'ease-in-out', // CSS transition easing function
            opener: function opener(openerElement) {
                return openerElement.is('img') ? openerElement : openerElement.find('img');
            }
        }
    });
    // SMOOTH SCROLL
    $(function () {
        $('.custom-navbar .nav_c a').on('click', function (event) {
            var $anchor = $(this);
            $('html, body').stop().animate({
                scrollTop: $($anchor.attr('href')).offset().top - 49
            }, 1000);
            event.preventDefault();
        });

        // 初始化地区选择器
        $.getJSON("/json/area.json").done(function (res) {
            $("#address").iPicker({
                data: res,
                width: 170,
                height: 55,
                onSelect: function (v) {
                    $("#area").val(v.join())
                }
            });
            var areaJson = JSON.parse(localStorage.getItem('areaJson'));
            if (!areaJson) {
                localStorage.setItem('areaJson', JSON.stringify({
                    date: new Date().getDate(),
                    json: res
                }))
            } else if (areaJson.date > new Date().getDate) {
                localStorage.setItem('areaJson', JSON.stringify({
                    date: new Date().getDate(),
                    json: res
                }))
            }
        });

        // 字段验证
        $("#qrcodeForm").validate({
            ignore: "",
            rules: {
                name: { required: true, maxlength: 32},
                content: {required: true, maxlength: 512},
                addressId: {required: true, maxlength: 64},
                info: {maxlength: 16}
            },
            messages: {
                name: {
                    required: "请输入二维码名称",
                    maxlength: "二维码名称最多为 32 个字符"
                },
                content: {
                    required: "请输入网址",
                    maxlength: "网址过长, 最多输入 512 字符"
                },
                addressId: {
                    required: "请选择地区",
                    maxlength: "地区id过长, 最多 64 个字符"
                },
                info: {
                    maxlength: "备注过长, 最多 16 个字符"
                }
            }
        });
    });
    start();
})(jQuery);

function start() {
    if (localStorage.u) {
        $('.user').show();
        $('.un_user').hide();
        $('.user_name').text(localStorage.u);
        menu();
    } else {
        $('.user').hide();
        $('.un_user').show();
    }
    //注册
    $('#register_btn').on('click', function () {
        register();
    });
    //登录
    $('#login_btn').on('click', function () {
        login();
    });
    //添加
    $("#add_code").on('click', function () {
        add_code();
    });
    //删除
    $("#delete_code").on('click', function () {
        delete_code();
    });
    //修改
    $("#fix_code").on('click', function () {
        fix_code();
    });
    //查询
    $("#search_code").on('click', function () {
        search_code();
    });
    //游客
    $('#visit').on('click', function () {
        visit();
    });
}

function visit() {
    var u = '游客';
    var p = '888888';
    $.ajax({
        url: base_url + '/php/login.php',
        data: 'u=' + u + '&p=' + p,
        success: function success(msg) {
            var data = JSON.parse(msg);
            if (data.code == '1') {
                console.log('ok');
                localStorage.u = data.user;
                $('.user').show();
                $('.un_user').hide();
                $('.user_name').text(localStorage.u);
                menu();
            } else {
                $('#visit a').html('系统错误!');
            }
        }

    });
}

function search_code() {
    var u = localStorage.u;
    var a = $('#search_address').val().trim();
    var n = $('#search_name').val().trim();
    var c = $('#search_url').val().trim();
    $.get(base_url + '/manage/search', {
        userId: localStorage.user.id,
        address: a,
        name: n,
        content: c
    }, function (r) {
        if (!r.state) {
            return swal(r.msg)
        }
        swal("success")
    });
    $.ajax({
        url: base_url + '/search',
        data: 'u=' + u + '&c=' + c + '&a=' + a + '&n=' + n,
        success: function success(msg) {
            var data = JSON.parse(msg);
            if (data.code == '1') {
                add_content(msg);
            } else {
                $('#modal-form3').modal('show');
                $('#modal-form3 .info_text').text('未找到符合条件的条目!');
            }
        }

    });

}

function fix_code() {
    var u = localStorage.u;
    var i = localStorage.i;
    var a = $('#fix_address').val();
    var n = $('#fix_name').val();
    var c = $('#fix_url').val();
    var i2 = $('#fix_info').val();
    if (!n || !c) {
        $('#modal-form3').modal('show');
        $('#modal-form3 .info_text').text('请完整输入');

        return;
    }
    $.ajax({
        url: base_url + '/php/fix.php',
        method: "POST",
        data: {
            u: u,
            c: c,
            a: a,
            n: n,
            i: i
        },
        success: function success(msg) {
            var data = JSON.parse(msg);
            if (data.code == '1') {
                $('#modal-form3').modal('show');
                $('#modal-form3 .info_text').text('修改成功!');

                location.reload();
            } else {
                $('#modal-form3').modal('show');
                $('#modal-form3 .info_text').text('修改失败!');
            }
        }

    });
}

function delete_code() {
    var u = localStorage.u;
    var i = localStorage.i;
    $.ajax({
        url: base_url + '/php/delete.php',
        data: 'u=' + u + '&i=' + i,
        success: function success(msg) {
            var data = JSON.parse(msg);
            if (data.code == '1') {
                $('#modal-form3').modal('show');
                $('#modal-form3 .info_text').text('删除成功!');

                location.reload();
            } else {
                $('#modal-form3').modal('show');
                $('#modal-form3 .info_text').text('删除失败!');
            }
        }
    });
}

function add_code() {
    var user = JSON.parse(localStorage.getItem('user'));
    if (!user) {
        swal('未登录, 请先登录');
        return start();
    }
    console.log(localStorage.getItem('user'));
    console.log(localStorage.user);
    console.log(localStorage.user.username);
    var $form = $("#qrcodeForm");
    console.log($form);
    $form.ajaxSubmit({
        url: base_url + '/manage/add',
        type: "POST",
        datatype: "json",
        resetForm: true,
        data: {
            userId: user.id,
            username: user.username
        },
        beforeSubmit: function () {
            return $form.valid();
        },
        success: function (data) {
            checkError(data, () => {
                swal('添加成功');
                location.reload();
            });
        }
    })
}

function login() {
    var username = $('#user_g').val();
    var password = $('#password_g').val();
    $.cpost(base_url + '/login', {username: username, password: password}, function (id) {
        localStorage.u = username;
        localStorage.setItem('user', JSON.stringify({id: id, username:username}));
        console.log(localStorage.getItem('user'));
        // 登录成功
        menu();
        $("#modal-form").modal('hide');
        $('.user').show();
        $('.un_user').hide();
        $('.user_name').text(username);
    });
}

function register() {
    var u = $('#user_gr').val() || 'test';
    var p = $('#password_gr').val() || 'test';
    $.cpost(base_url + '/register', {username: u, password: p}, (data)=>{
        // cpost 没弹出错误框就代表无错误
        swal('注册成功');
        // 打开登录模态框
        $('#modal-form-register').modal('hide');
        $('.user').show();
        $('.un_user').hide();
        $('.user_name').text(localStorage.u);
        $('#register_info').html(' ');
        menu();
    });
}

function add_content(dataList) {
    $('.code_info').remove();
    // 获取地区数据
    var areaJson = JSON.parse(localStorage.getItem('areaJson'));
    for (var i = 0; i < dataList.length; i++) {
        var id = dataList[i].id;
        var code_id2 = '';
        if (id > 99) {
            code_id2 = id;
        } else if (id > 9 && id <= 99) {
            code_id2 = '0' + id;
        } else {
            code_id2 = '00' + id;
        }
        var addressId = dataList[i].addressId;
        var name = dataList[i].name;
        var content = dataList[i].content;
        var viewNum = dataList[i].viewNum;
        var info = dataList[i].info;

        var $div = $("<div class='col-md-6 col-sm-6 code_info'></div>");
        var $thumb = $("<div class='media blog-thumb'></div>");
        var $info = $("<div class='media-body blog-info picker'></div>")
          .append(`<small><i class="fa fa-clock-o"></i>扫码次数: ${viewNum}</small>`)
          .append(`<h3><a href="javascript: void(0)">${name}</a></h3>`)
          .append(`<p>I D : ${code_id2}</p>`)
          .append(`<p>网址: ${content}</p>`)
          .append(`<p>备注: ${info ? info : '无'}</p>`)
          .append(`<p id="area-${id}">无</p>`);
        // 编辑按钮
        var $button = $("<button class='btn section-btn'>编辑</button>").on("click", function () {
            fix(id, addressId, name, content, info);
        });
        $info.append($button);
        var url = 'http:' + base_url + '/view?id=' + dataList[i].id + '&&qq=228322991&v=0.2.1';
        var $qrCode = $("<div class='media-object media-left ele0'></div>").qrcode({
            render: "canvas", //也可以替换为table
            width: 200,
            height: 200,
            text: url
        });
        $thumb.append($qrCode).append($info);
        $div.append($thumb);
        $('#code_b').append($div);
        // 选中地区
        if (addressId) {
            let ids = addressId.split(',');
            $(`#area-${id}`).iPicker({
                data: areaJson.json,
                level: ids.length,
                disabled: [0, 1, 2],
                defaultValue: ids,
                width: 20,
            })
        }
    }
}

function menu() {
    console.log('menu');
    $.cget(base_url + '/manage/content', {username: localStorage.u}, function (data) {
        add_content(data);
    });
}

function fix(id, a, n, c, i) {
    localStorage.i = id;
    console.log("fix", id, a, n, c, i);
    $('#fix_address').val(decodeURI(a));
    $('#fix_name').val(decodeURI(n));
    $('#fix_url').val(decodeURI(c));
    $('#fix_info').val(decodeURI(i));
    $('#modal-form2').modal('show');
}

function checkError(data, callback) {
    if(data.state == 2) {
        swal('未登录, 请先登录');
        return flushStatus();
    }
    if (data.state == 0) {
        return swal(data.msg);
    }
    if (typeof callback == "function"){
        callback(data.obj);
    }
}

function flushStatus() {
    delete localStorage.u;
    delete localStorage.user;
    start();
}

function exit() {
    delete localStorage.u;
    delete localStorage.user;
    location.reload();
}
