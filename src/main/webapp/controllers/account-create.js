(function() {
    /**
     * Config
     */
    var moduleName = 'com.github.jlgrock.informatix.workmanager';
    module = angular.module(moduleName);

    module.controller('accountCreateCtrl',
        function ($scope, $http, $log, $location, stateKeeper, processResponse) {
            /**
             * Handle State based info
             */
            $scope.error = stateKeeper.error;

            /**
             * Define data for binding
             */
            $scope.possibleRoles = ['ADMIN', 'USER'];

            $scope.save = function () {
                $log.log("save add [" + $scope.userparams.fname + ", " + $scope.userparams.lname + ", "
                    + $scope.userparams.email + "," + $scope.selectedRole + "]");

                var data = {
                    fname: $scope.userparams.fname,
                    lname: $scope.userparams.lname,
                    email: $scope.userparams.email,
                    role: $scope.selectedRole
                };

                $http.put('accounts', data).then(
                    function () {
                        $log.info("Add Succeeded");
                        $location.path("/accounts");
                    },
                    function (response) {
                        $log.error("Unable to add User ");
                        processResponse.processErrorResponse(response);
                    }
                );
            };

            $scope.setRole = function (role) {
                $scope.selectedRole = role;
            };

            $scope.cancel = function() {
                $location.path( "/accounts" );
            };

            /**
             * Execute the things that need to be run at startup
             */
            stateKeeper.clearAll();
        }
    );
})();