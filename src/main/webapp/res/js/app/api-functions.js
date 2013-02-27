define(['jquery', 'underscore', 'base64', 'app/tmpl', 'app/ajax-utils'], function($, _, base64, tmpl, ajax) {
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

        ajax.req({url: '/api/login', method: 'POST', username: username, password: password, doneCallback: successFunc, failCallback: failFunc});
    };

    api.loadLinkGroups = function()
    {
        tmpl.fetch(tmpl.LINK_GROUP_TMPL_URL, function(t) {
            ajax.req({
                url: '/api/linkGroups',
                doneCallback: function(linkGroups) {
                    _.each(linkGroups, function(linkGroup) {
                        var rendered = _.template(t, {linkGroup: linkGroup});

                        $('#linkGroups').append(rendered);

                        api.loadLinks(linkGroup.id);
                    });
                }
            });
        });
    };

    api.loadLinks = function(groupId)
    {
        tmpl.fetch(tmpl.LINK_TMPL_URL, function(t) {
            ajax.req({
                url: '/api/links',
                data: {groupId: groupId},
                doneCallback: function(links) {
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
                }
            });
        });
    };

    api.addLink = function(link, successCallback)
    {
        ajax.req({method: 'POST', url: '/api/links', data: link, dataType: ajax.DataType.JSON, doneCallback: successCallback});
    };

    api.addLinkGroup = function(linkGroup, successCallback)
    {
        ajax.req({method: 'POST', url: '/api/linkGroups', data: linkGroup, dataType: ajax.DataType.JSON, doneCallback: successCallback});
    };

    return api;
});