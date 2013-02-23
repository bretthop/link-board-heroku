define(['jquery', 'app/tmpl', 'app/api-functions', 'bootstrap/js/bootstrap'], function($, tmpl, api) {
    var frontend = {};

    frontend.registerHandlers = function()
    {
        $('#showAddLinkBtn').live('click', function() {
            frontend.showAddLinkModal();
        });

        $('#addLinkBtn').live('click', function() {
            frontend.handleAddLinkClick();
        });
    };

    frontend.handleAddLinkClick = function()
    {
        // TODO: Add validation
        var formValid = true;

        if (formValid) {
            var newLink = {
                title: $('#addLinkForm #title').val(),
                href: $('#addLinkForm #href').val(),
                description: $('#addLinkForm #description').val()
            };

            api.addLink(newLink, frontend.hideAddLinkModal);
        }
    };

    frontend.hideAddLinkModal = function()
    {
        $('#addLinkModal').modal('hide');
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

    return frontend;
});