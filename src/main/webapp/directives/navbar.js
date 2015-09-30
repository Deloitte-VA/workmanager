(function() {
    /**
     * Config
     */
    var moduleName = 'com.github.jlgrock.informatix.workmanager';
    module = angular.module(moduleName);

    module .directive('navbar', function() {
            return {
                restrict: 'E',
                replace: true,
                transclude: true,
                templateUrl: '/templates/navbar.html'
            }});
})();