define(['jquery'], function($) {
    var ajax = {};

    ajax.DataType = {
        DEFAULT: 0,
        JSON: 1
    };

    ajax.req = function(params)
    {
        var url = params.url,
            authenticate = params.authenticate != undefined ? params.authenticate : true,
            username = params.username || sessionStorage.getItem('username'),
            password = params.password || sessionStorage.getItem('password'),
            method = params.method,
            data = params.data || '',
            dataType = params.dataType || ajax.DataType.DEFAULT,
            doneCallback = params.doneCallback,
            failCallback = params.failCallback;

        var ajaxDescriptor = {
            url: url,
            type: method,
            data: data
        };

        if (authenticate) {
            if (username && password) {
                // TODO: Add base64 encoding once server can handle it
                var authToken = username + ':' + password;

                ajaxDescriptor.beforeSend = function (xhr) { // TODO: See if you can set this by the 'headers' prop
                    xhr.setRequestHeader ('Authorization', 'Basic ' + authToken);
                };
            }
        }

        if (dataType && dataType == ajax.DataType.JSON) {
            ajaxDescriptor.contentType = 'application/json';
            ajaxDescriptor.data = JSON.stringify(data);
        }

        $.ajax(ajaxDescriptor)
            .done(function(data) {
                if (doneCallback) {
                    doneCallback(data);
                }
            })
            .fail(function(data) {
                if (failCallback) {
                    failCallback(data);
                }
            });
    };

    return ajax;
});