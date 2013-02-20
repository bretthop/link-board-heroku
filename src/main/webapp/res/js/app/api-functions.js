define(['jquery', 'underscore', 'app/tmpl'], function($, _, tmpl) {
    var api = {};

    api.loadLinks = function()
    {
        tmpl.fetch(tmpl.LINK_TMPL_URL, function(t) {
            $.ajax({url: '/linkServlet'})
                .success(function(links) {
                    _.each(links, function(link) {
                        var rendered = _.template(t, {link: link});

                        $('#links').append(rendered);
                    });
                })
        });
    };

    return api;
});