(function() {
    /**
     * Config
     */
    var moduleName = 'com.github.jlgrock.informatix.workmanager';
    module = angular.module(moduleName);

    module.controller('alertBarCtrl',
        function($scope, stateKeeper) {
            $scope.error = stateKeeper.error;
        }
    );
})();