app.controller('security',
    function($rootScope, $scope, $http, $location, $route, securityService, responseService) {

        $scope.tab = function(route) {
            return $route.current && route === $route.current.controller;
        };

        securityService.authenticate();

        $scope.credentials = {};
        $scope.login = function() {
            securityService.authenticate($scope.credentials, function(authenticated) {
                if (authenticated) {
                    console.log("Login succeeded")
                    $scope.error = false;
                    $rootScope.authenticated = true;
                    $location.path("/");
                } else {
                    console.log("Login failed")
                    $scope.error = true;
                    $rootScope.authenticated = false;
                    $location.path("/login");
                }
            })
        };

        $scope.goto = function(path) {
            $location.path(path);
        }

        $scope.logout = function() {
            $http.post('logout', {}).then(function() {
                $rootScope.authenticated = false;
                $location.path("/");
            }, function() {
                responseService.loginError($scope, "Logout failed");
            });
        }

        $scope.forgotPassword = function() {
            var config = {};
            config.params = { email : $scope.credentials.forgottenEmail };
            $http.get('accounts/reset', config).then(function() {
                $rootScope.authenticated = false;
                $location.path("/");
            }, function(response) {
                responseService.processErrorResponse($scope, response);
            });
        }

        $scope.toggleForgotPasswordForm = function() {
            $scope.forgotPasswordForm = true;
        };

        $scope.cancelAll = function() {
            $scope.credentials = {};
            $scope.errors = false;
            $scope.forgotPasswordForm = false;
        };

    }
);