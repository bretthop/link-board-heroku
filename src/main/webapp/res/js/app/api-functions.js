define(['jquery', 'underscore', 'app/tmpl'], function($, _, tmpl) {
    var api = {};

    api.loadLinkGroups = function()
    {
        tmpl.fetch(tmpl.LINK_GROUP_TMPL_URL, function(t) {
            $.ajax({url: '/linkGroupServlet'})
                .success(function(linkGroups) {
                    _.each(linkGroups, function(linkGroup) {
                        var rendered = _.template(t, {linkGroup: linkGroup});

                        $('#linkGroups').append(rendered);

                        api.loadLinks(linkGroup.id);
                    });
                })
        });
    };

    api.loadLinks = function(groupId)
    {
        tmpl.fetch(tmpl.LINK_TMPL_URL, function(t) {
            $.ajax({url: '/linkServlet', data: {groupId: groupId}})
                .success(function(links) {
                    if (links.length > 0) {
                        _.each(links, function(link) {
                            var rendered = _.template(t, {link: link});

                            $('#linksForGroup_' + groupId).append(rendered);
                        });
                    }
                    else {
                        $('#linksForGroup_' + groupId).append("<p>There are no links in this group!</p>");
                    }
                })
        });
    };

    api.addLink = function(link, successCallback)
    {
        // TODO: Change content-type to JSON (once server can handle it)
        //$.ajax({type: 'POST', url: '/linkServlet', contentType: 'application/json', data: JSON.stringify(link)})
        $.ajax({type: 'POST', url: '/linkServlet', data: link})
            .success(function() {
                if (successCallback) {
                    successCallback();
                }
            });
    };

    api.addLinkGroup = function(linkGroup, successCallback)
    {
        // TODO: Change content-type to JSON (once server can handle it)
        //$.ajax({type: 'POST', url: '/linkServlet', contentType: 'application/json', data: JSON.stringify(link)})
        $.ajax({type: 'POST', url: '/linkGroupServlet', data: linkGroup})
            .success(function() {
                if (successCallback) {
                    successCallback();
                }
            });
    };

    return api;
});