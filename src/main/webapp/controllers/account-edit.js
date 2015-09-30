(function() {
    /**
     * Config
     */
    var moduleName = 'com.github.jlgrock.informatix.workmanager';
    module = angular.module(moduleName);

    module.controller('accountEditCtrl',
        function ($scope, $routeParams, $http, $log, $location, stateKeeper, processResponse) {
            /**
             * Handle State based info
             */
            $scope.error = stateKeeper.error;
            $scope.userparams = {};

            /**
             * Define data for binding
             */
            $scope.possibleRoles = ['ADMIN', 'USER'];
            $scope.id = $routeParams.id;

            $scope.load = function (id) {
                $log.debug("edit user=" + $scope.id);

                $http.get('accounts/' + $scope.id).then(
                    function(response){
                        var person = response.data;
                        $scope.userparams.id = person.id;
                        $scope.userparams.fname = person.fname;
                        $scope.userparams.lname = person.lname;
                        $scope.userparams.email = person.email;
                        $scope.selectedRole = person.role;
                    },
                    function(){
                        $log.error("Unable to delete User " + id);
                        processResponse.processErrorResponse(response);
                    }
                );
            };


            $scope.save = function() {
                $log.log("save edit user=" + $scope.id);

                var id = $scope.userparams.id;
                var data = {
                    fname: $scope.userparams.fname,
                    lname: $scope.userparams.lname,
                    email: $scope.userparams.email,
                    role: $scope.selectedRole
                };

                $http.post('accounts/' + id, data).then(
                    function () {
                        $location.path( "/accounts" );
                    }, function (response) {
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
            $scope.load();
        }
    );
})();