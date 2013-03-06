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
    },
    // Stops RequireJS from caching JS files (very annoying during dev),
    // by adding a new url arg to each javascript file based on time (so it
    // wont be able to recognise that it's cached this file).
    // SHOULD BE REMOVED IN PRODUCTION ENVIRONMENTS
    // See: http://stackoverflow.com/questions/8315088/prevent-requirejs-from-caching-required-scripts
    urlArgs: "bust=" + (new Date()).getTime()
});

// Start the main app logic.
//
// Adding 'bootstrap/js/bootstrap' to the required modules list adds bootstrap's functionality to our context
// (this means we can use method like $().modal() here)
// (This is only left here as a reference)
requirejs(['app/api-functions', 'app/frontend', 'bootstrap/js/bootstrap'],
    function (api, frontend) {
        frontend.registerHandlers();

        if (!sessionStorage.getItem('username') || !sessionStorage.getItem('password')) {
            frontend.showLoginModal();
        }
        else {
            api.loadLinkGroups();
        }
    });