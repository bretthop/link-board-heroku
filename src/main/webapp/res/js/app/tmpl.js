define(['jquery'], function($) {
    var tmpl = {};

    tmpl.LINK_TMPL_URL = '/templates/linkTmpl.html';

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
// TODO: Maybe move these to an app config file
//var LINK_TMPL_URL = '/templates/linkTmpl.html';
//
//tmplCache = {};
//
//function fetchTmpl(url, callback)
//{
//    if (tmplCache[url] != undefined) {
//        callback(tmplCache[url]);
//
//        return;
//    }
//
//    $.get(url, function(tmpl) {
//        tmplCache[url] = tmpl;
//
//        callback(tmpl);
//    });
//}