define(['jquery'], function($) {
    var tmpl = {};

    tmpl.LINK_TMPL_URL = '/templates/linkTmpl.html';
    tmpl.LINK_GROUP_TMPL_URL = '/templates/linkGroupTmpl.html';
    tmpl.ADD_LINK_TMPL_URL = '/templates/addLinkTmpl.html';
    tmpl.ADD_LINK_GROUP_TMPL_URL = '/templates/addLinkGroupTmpl.html';
    tmpl.LOGIN_MODAL_TMPL_URL = '/templates/loginTmpl.html';
    tmpl.SIGN_UP_MODAL_TMPL_URL = '/templates/signUpTmpl.html';

    tmpl.cache = {};

    tmpl.fetch = function(url, callback)
    {
        if (tmpl.cache[url] != undefined) {
            callback(tmpl.cache[url]);

            return;
        }

        $.get(url, function(t) {
            tmpl.cache[url] = t;

            callback(t);
        });
    };

    return tmpl;
});