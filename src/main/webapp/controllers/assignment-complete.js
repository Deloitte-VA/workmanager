(function() {
    /**
     * Config
     */
    var moduleName = 'com.github.jlgrock.informatix.workmanager';
    module = angular.module(moduleName);

    module.controller('assignmentCompleteCtrl',
        function ($scope, $log, $http, $location, $routeParams, stateKeeper, fileUtils, processResponse) {
            /**
             * Handle State based info
             */
            $scope.error = stateKeeper.error;

            $scope.file = null;
            $scope.assignmentId = parseInt($routeParams.assignmentId, 10);

            if (isNaN($scope.assignmentId)) {
                processResponse.printError("Cannot process with invalid assignment Id");
                return;
            }

            $scope.$watch('file', function (newVal) {
                if (newVal) {
                    $log.log(newVal);
                }
            });

            var success = function(){
                $location.path( "/assignments");
            };


            $scope.upload = function() {
                var params = {
                    id: $scope.assignmentId
                };
                fileUtils.uploadFileToUrl({
                    uploadUrl: '/assignments/' + $scope.assignmentId,
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
        }
    );
})();