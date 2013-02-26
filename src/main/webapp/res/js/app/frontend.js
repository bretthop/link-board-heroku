define(['jquery', 'app/tmpl', 'app/api-functions', 'bootstrap/js/bootstrap'], function($, tmpl, api) {
    var frontend = {};

    frontend.registerHandlers = function()
    {
        $('#loginBtn').live('click', function() {
            frontend.handleLoginClick();
        });

        $('#showAddLinkBtn').live('click', function() {
            frontend.showAddLinkModal();
        });

        $('#addLinkBtn').live('click', function() {
            frontend.handleAddLinkClick();
        });

        $('#showAddLinkGroupBtn').live('click', function() {
            frontend.showAddLinkGroupModal();
        });

        $('#addLinkGroupBtn').live('click', function() {
            frontend.handleAddLinkGroupClick();
        });
    };

    frontend.handleLoginClick = function()
    {
        // TODO: Add validation
        var formValid = true;

        if (formValid) {
            var username = $('#username').val();
            var password = $('#password').val();

            api.login(username, password, frontend.handleSuccessfulLogin)
        }
    };

    frontend.handleAddLinkClick = function()
    {
        // TODO: Add validation
        var formValid = true;

        if (formValid) {
            var newLink = {
                groupId: $('#addLinkForm #groupId').val(),
                title: $('#addLinkForm #title').val(),
                href: $('#addLinkForm #href').val(),
                description: $('#addLinkForm #description').val()
            };

            api.addLink(newLink, frontend.handleSuccessfulAddLink);
        }
    };

    frontend.handleAddLinkGroupClick = function()
    {
        // TODO: Add validation
        var formValid = true;

        if (formValid) {
            var newLinkGroup = {
                title: $('#addLinkGroupForm #title').val(),
                description: $('#addLinkGroupForm #description').val()
            };

            api.addLinkGroup(newLinkGroup, frontend.handleSuccessfulAddLinkGroup);
        }
    };

    frontend.handleSuccessfulLogin = function()
    {
        api.loadLinkGroups();

        $('#loginModal').modal('hide');
    };

    frontend.handleSuccessfulAddLink = function()
    {
        $('#linkGroups').html('');
        api.loadLinkGroups();

        $('#addLinkModal').modal('hide');
    };


    frontend.handleSuccessfulAddLinkGroup = function()
    {
        $('#linkGroups').html('');
        api.loadLinkGroups();

        $('#addLinkGroupModal').modal('hide');
    };

    frontend.showLoginModal = function()
    {
        tmpl.fetch(tmpl.LOGIN_MODAL_TMPL_URL, function(tmpl)
        {
            if ($('#loginModalHolder').length == 0) {
                $('body').append($('<div id="loginModalHolder"></div>'));
            }

            // Set the login template into the dom
            $('#loginModalHolder').html(tmpl);

            $('#loginModal').modal();
        });
    };

    frontend.showAddLinkModal = function()
    {
        tmpl.fetch(tmpl.ADD_LINK_TMPL_URL, function(t) {
            if ($('#addLinkTmplHolder').length == 0) {
                $('body').append($('<div id="addLinkTmplHolder"></div>'));
            }

            // Set the login template into the dom
            $('#addLinkTmplHolder').html(t);

            $('#addLinkModal').modal();
        });
    };

    frontend.showAddLinkGroupModal = function()
    {
        tmpl.fetch(tmpl.ADD_LINK_GROUP_TMPL_URL, function(t) {
            if ($('#addLinkGroupTmplHolder').length == 0) {
                $('body').append($('<div id="addLinkGroupTmplHolder"></div>'));
            }

            // Set the login template into the dom
            $('#addLinkGroupTmplHolder').html(t);

            $('#addLinkGroupModal').modal();
        });
    };

    return frontend;
});