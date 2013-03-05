define(['jquery', 'app/tmpl', 'app/api-functions', 'bootstrap/js/bootstrap'], function($, tmpl, api) {
    var frontend = {};

    frontend.registerHandlers = function()
    {
        /* Sign Up */
        $('#showSignUpFormBtn').live('click', frontend.showSignUpModal);
        $('#signUpBtn').live('click', frontend.handleSignUpClick);

        /* Login */
        $('#loginBtn').live('click', frontend.handleLoginClick);

        /* Add Link */
        $('#showAddLinkBtn').live('click', frontend.showAddLinkModal);
        $('#addLinkBtn').live('click', frontend.handleAddLinkClick);

        /* Add Link Group */
        $('#showAddLinkGroupBtn').live('click', frontend.showAddLinkGroupModal);
        $('#addLinkGroupBtn').live('click', frontend.handleAddLinkGroupClick);

        /* Delete Link */
        $('.deleteLinkBtn').live('click', function(evt) { frontend.handleDeleteLinkClick(evt.currentTarget); });
    };

    /**
     * Sign Up
     * */
    frontend.showSignUpModal = function()
    {
        tmpl.fetch(tmpl.SIGN_UP_MODAL_TMPL_URL, function(tmpl)
        {
            if ($('#signUpModalHolder').length == 0) {
                $('body').append($('<div id="signUpModalHolder"></div>'));
            }

            // Set the login template into the dom
            $('#signUpModalHolder').html(tmpl);

            // Before we show the sign up modal, check if there is a login
            // modal on the page and make sure its hidden
            if ($('#loginModal').length > 0) {
                $('#loginModal').modal('hide');
            }

            $('#signUpModal').modal();
        });
    };

    frontend.handleSignUpClick = function()
    {
        // TODO: Add validation
        var formValid = true;

        if (formValid) {
            var newUser = {
                username:  $('#signUpModal #username').val(),
                password:  $('#signUpModal #password').val(),
                email:     $('#signUpModal #email').val(),
                firstName: $('#signUpModal #firstName').val(),
                lastName:  $('#signUpModal #lastName').val()
            };

            api.signUp(newUser, frontend.handleSuccessfulSignUp)
        }
    };

    frontend.handleSuccessfulSignUp = function()
    {
        api.loadLinkGroups();

        $('#signUpModal').modal('hide');
    };

    /**
     * Login
     * */
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

    frontend.handleLoginClick = function()
    {
        // TODO: Add validation
        var formValid = true;

        if (formValid) {
            var username = $('#loginModal #username').val();
            var password = $('#loginModal #password').val();

            api.login(username, password, frontend.handleSuccessfulLogin)
        }
    };

    frontend.handleSuccessfulLogin = function()
    {
        api.loadLinkGroups();

        $('#loginModal').modal('hide');
    };

    /**
     * Add Link
     * */
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

    frontend.handleAddLinkClick = function()
    {
        // TODO: Add validation
        var formValid = true;

        if (formValid) {
            var newLink = {
                group: {
                    id: $('#addLinkForm #groupId').val()
                },
                title: $('#addLinkForm #title').val(),
                href: $('#addLinkForm #href').val(),
                description: $('#addLinkForm #description').val()
            };

            api.addLink(newLink, frontend.handleSuccessfulAddLink);
        }
    };

    frontend.handleSuccessfulAddLink = function()
    {
        $('#linkGroups').html('');
        api.loadLinkGroups();

        $('#addLinkModal').modal('hide');
    };

    /**
     * Add Link Group
     * */
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

    frontend.handleSuccessfulAddLinkGroup = function()
    {
        $('#linkGroups').html('');
        api.loadLinkGroups();

        $('#addLinkGroupModal').modal('hide');
    };

    /**
     * Delete Link
     * */
    frontend.handleDeleteLinkClick = function(triggerEl)
    {
        var linkId = triggerEl.id;

        api.deleteLink(linkId, frontend.handleSuccessfulDeleteLink);
    };

    frontend.handleSuccessfulDeleteLink = function()
    {
        // TODO: Make more efficient
        $('#linkGroups').html('');
        api.loadLinkGroups();
    };

    return frontend;
});