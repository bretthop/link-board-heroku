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

    api.addLink = function(link, successCallback)
    {
        // TODO: Change content-type to JSON (once server can handle it)
        //$.ajax({type: 'POST', url: '/linkServlet', contentType: 'application/json', data: JSON.stringify(link)})
        $.ajax({type: 'POST', url: '/linkServlet', data: link})
            .success(function() {
                $('#links').html('');

                api.loadLinks();

                if (successCallback) {
                    successCallback();
                }
            });
    };

    return api;
});