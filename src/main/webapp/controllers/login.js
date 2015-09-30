(function() {
    /**
     * Config
     */
    var moduleName = 'com.github.jlgrock.informatix.workmanager';
    module = angular.module(moduleName);

    module.controller('securityCtrl',
        function ($rootScope, $scope, $http, $log, $location, $route, security, stateKeeper, processResponse) {
            /**
             * Handle State based info
             */
            $scope.error = stateKeeper.error;

            $scope.forgotPassword = false;
            $scope.credentials = {};

            $scope.login = function () {
                security.authenticate($scope.credentials, function (authenticated) {
                    if (authenticated) {
                        $rootScope.authenticated = true;
                        $log.log("Login succeeded")
                        $location.path("/");
                    } else {
                        $rootScope.authenticated = false;
                        processResponse.printError("Login failed");
                        $location.path("/security");
                    }
                })
            };

            $scope.logout = function () {
                $http.post('logout', {}).then(function () {
                    $rootScope.authenticated = false;
                    $location.path("/");
                }, function () {
                    processResponse.printError("Logout failed");
                });
            }

            $scope.resetPassword = function () {
                var config = {};
                config.params = {email: $scope.credentials.forgottenEmail};
                $http.get('accounts/reset', config).then(function () {
                    $rootScope.authenticated = false;
                    $location.path("/");
                }, function (response) {
                    processResponse.processErrorResponse(response);
                });
            };

            $scope.toggleForgotPassword = function() {
                $scope.forgotPassword = !$scope.forgotPassword;
            };

            /**
             * Execute the things that need to be run at startup
             */
            stateKeeper.clearAll();
            security.authenticate();

        }
    );
})();