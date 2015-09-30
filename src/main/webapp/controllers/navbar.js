(function() {
    /**
     * Config
     */
    var moduleName = 'com.github.jlgrock.informatix.workmanager';
    module = angular.module(moduleName);

    module.controller('navbarCtrl',
        function ($rootScope, $scope, $http, $log, $location, security, processResponse) {
            $scope.goto = function(url) {
                $location.path(url);
            };

            $scope.logout = function () {
                $http.post('/logout').then(
                    function () {
                        $rootScope.authenticated = false;
                        $log.info("Successfully logged out");
                        $location.path("/login");
                    },
                    function () {
                        processResponse.printError("Logout failed");
                    }
                );
            };

            /**
             * Execute the things that need to be run at startup
             */
            security.authenticate();

        }
    );
})();