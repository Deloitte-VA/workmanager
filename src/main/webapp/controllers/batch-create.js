(function() {
    /**
     * Config
     */
    var moduleName = 'com.github.jlgrock.informatix.workmanager';
    module = angular.module(moduleName);

    module.controller('batchCreateCtrl',
        function ($scope, $log, $http, $location, stateKeeper, processResponse, fileUtils) {
            /**
             * Handle State based info
             */
            $scope.error = stateKeeper.error;

            /**
             * Define data for binding
             */
            $scope.file = null;


            $scope.$watch('file', function (newVal) {
                if (newVal) {
                    $log.log(newVal);
                }
            });

            var success = function(){
                $location.path( "/assignments");
            };

            $scope.upload = function() {
                fileUtils.uploadFileToUrl({
                    file: $scope.file,
                    uploadUrl: '/batches',
                    success: success
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