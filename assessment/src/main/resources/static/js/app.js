(function () {

    "use strict";

    //create the module
    var App = angular.module('my-app', ["ngRoute"])
        .config(function ($routeProvider, $locationProvider) {
            $locationProvider.hashPrefix('');

           

        });

})();
