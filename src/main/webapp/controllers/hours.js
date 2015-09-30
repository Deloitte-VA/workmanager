(function() {
    /**
     * Config
     */
    var moduleName = 'com.github.jlgrock.informatix.workmanager';
    module = angular.module(moduleName);

    module.controller('hoursCtrl',
        function($scope, $http, $routeParams, $log, $location, processResponse, stateKeeper) {
            /**
             * Handle State based info
             */
            $scope.error = stateKeeper.error;

            /**
             * Define data for binding
             */
            $scope.assignmentId = parseInt($routeParams.assignmentId, 10);

            $scope.getAssignment = function() {
                $http.get('assignments/' + $scope.assignmentId).then(
                    function (response) {
                        $log.debug("Retrieved Assignment " + $scope.assignmentId);
                        $scope.assignment = response.data
                    }, function (response) {
                        $log.error("Unable to get assignment " + $scope.assignmentId);
                        processResponse.processErrorResponse(response);
                    }
                );
            };

            $scope.save = function() {
                var data = {
                    hours: $scope.assignment.billableHours.toString()
                };

                $http.post('/assignments/' + $scope.assignmentId + '/hours', data).then(
                    function () {
                        $log.debug("Hours saved for assignment " + $scope.assignmentId);
                        $location.path("/assignments");
                    }, function (response) {
                        $log.error("Unable to save hours for assignment " + $scope.assignmentId);
                        processResponse.processErrorResponse(response);
                    }
                );
            };

            /**
             * Execute the things that need to be run at startup
             */
            stateKeeper.clearAll();
            $scope.getAssignment();

        }
    );
})();