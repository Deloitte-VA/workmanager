(function() {
    /**
     * Config
     */
    var moduleName = 'com.github.jlgrock.informatix.workmanager';
    module = angular.module(moduleName);

    module.controller('assignmentCreateCtrl',
        function ($scope, $log, $http, $location, $routeParams, stateKeeper, fileUtils, processResponse) {
            /**
             * Handle State based info
             */
            $scope.error = stateKeeper.error;

            $scope.file = null;
            $scope.people = [];
            $scope.batchId = parseInt($routeParams.batchId, 10);

            if (isNaN($scope.batchId)) {
                processResponse.printError("Cannot process with invalid batch Id");
                return;
            }

            $scope.$watch('file', function (newVal) {
                if (newVal) {
                    $log.log(newVal);
                }
            });

            $scope.getUsers = function() {
                $http.get('/accounts').then(
                    function(response) {
                        $scope.people = response.data;
                    },
                    function(response) {
                        $log.error("Unable to get Users for Assignment");
                        processResponse.processErrorResponse(response);
                    }
                )
            };

            var success = function(){
                $location.path( "/assignments");
            };


            $scope.upload = function() {
                var params = {
                    batchId: $scope.batchId,
                    userId: $scope.userSelected.id
                };
                fileUtils.uploadFileToUrl({
                    uploadUrl: '/assignments',
                    file: $scope.file,
                    success: success,
                    params: params
                });
            };

            $scope.cancel = function() {
                $location.path( "/assignments");
            };

            /**
             * Execute the things that need to be run at startup
             */
            stateKeeper.clearAll();
            $scope.getUsers();
        }
    );
})();