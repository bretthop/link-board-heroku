define(['jquery'], function($) {
    var tmpl = {};

    tmpl.LINK_TMPL_URL = '/templates/linkTmpl.html';
    tmpl.ADD_LINK_TMPL_URL = '/templates/addLinkTmpl.html';
    tmpl.ADD_LINK_GROUP_TMPL_URL = '/templates/addLinkGroupTmpl.html';

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