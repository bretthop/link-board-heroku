requirejs.config({
    //By default load any module IDs from js/lib
    baseUrl: 'res/js/lib',
    //except, if the module ID starts with "app",
    //load it from the js/app directory. paths
    //config is relative to the baseUrl, and
    //never includes a ".js" extension since
    //the paths config could be for a directory.
    paths: {
        app: '../app',
        bootstrap: '../../bootstrap'
    },
    // Shim is used to load javascript libs that
    // don't provide a define() method
    shim: {
        'underscore': {
            exports: '_'
        },
        'bootstrap/js/bootstrap': {
            deps: ['jquery']
        }
    }
});

// Start the main app logic.
//
// Adding 'bootstrap/js/bootstrap' to the required modules list adds bootstrap's functionality to our context
// (this means we can use method like $().modal() here)
requirejs(['app/api-functions', 'bootstrap/js/bootstrap'],
    function (api) {
        $('#loginModal').modal();
        api.loadLinks();
    });