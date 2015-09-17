var app = angular.module('hello', [ 'ngRoute', 'ngAnimate', 'ngCookies', 'ui.bootstrap' ]);

app.config(function($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
        templateUrl : 'templates/home.html',
        controller : 'home',
    }).when('/accounts', {
        templateUrl : 'templates/accounts.html',
        controller : 'accounts',
    }).when('/security', {
        templateUrl : 'templates/security.html'
    }).otherwise('/security');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

    $httpProvider.defaults.withCredentials = true;

});

//app.run(
//    function($rootScope, $location) {
//
//        // register listener to watch route changes
//        $rootScope.$on( "$routeChangeStart", function(event, next, current) {
//            if ( $rootScope.authenticated === undefined) {
//                authenticate();
//            }
//            if ( $rootScope.authenticated == false ) {
//                $location.path( "/login" );
//            }
//        })
//    }
//);