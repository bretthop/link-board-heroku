define(['jquery', 'underscore', 'base64', 'app/tmpl'], function($, _, base64, tmpl) {
    var api = {};

    api.login = function(username, password, successCallback)
    {
        $('.loginResult').addClass('hidden');

        var successFunc = function(user) {
            sessionStorage.setItem('username', username);
            sessionStorage.setItem('password', password);

            $('.loginResult').html('Success!')
                .addClass('text-success')
                .removeClass('hidden');

            if (successCallback) {
                successCallback();
            }
        };

        var failFunc = function() {
            sessionStorage.removeItem('username');
            sessionStorage.removeItem('password');

            $('.loginResult')
                .html('Failed!')
                .addClass('text-error')
                .removeClass('hidden');
        };

        // TODO: Add base 64 encoding
        var authToken = username + ':' + password;
//        var authToken = base64.encode(username + ':' + password);

        $.ajax({
            type: 'POST',
            url: 'loginServlet',
            beforeSend: function (xhr) { // TODO: See if you can set this by the 'headers' prop
                xhr.setRequestHeader ('Authorization', 'Basic ' + authToken);
            }
        }).done(function(data) {
                if (successFunc) {
                    successFunc(data);
                }
            }).fail(function() {
                if (failFunc) {
                    failFunc();
                }
            });
    };

    api.loadLinkGroups = function()
    {
        // TODO: Add base 64 encoding
        var authToken = sessionStorage.getItem('username') + ':' + sessionStorage.getItem('password');
//        var authToken = base64.encode(username + ':' + password);

        tmpl.fetch(tmpl.LINK_GROUP_TMPL_URL, function(t) {
            $.ajax({
                url: '/linkGroupServlet',
                beforeSend: function (xhr) { // TODO: See if you can set this by the 'headers' prop
                    xhr.setRequestHeader ('Authorization', 'Basic ' + authToken);
                }})
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
                    var rendered = '';

                    if (links.length > 0) {
                        _.each(links, function(link) {
                            rendered += _.template(t, {link: link});
                        });
                    }
                    else {
                        rendered = '<p>There are no links in this group!</p>';
                    }

                    $('#linksForGroup_' + groupId).html(rendered);
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