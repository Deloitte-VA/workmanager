(function() {
    /**
     * Config
     */
    var moduleName = 'com.github.jlgrock.informatix.workmanager';
    module = angular.module(moduleName);

    module.factory('processResponse', function ($log, $stateKeeper) {
        var obj = {};

        obj.processErrorResponse = function (response) {
            $stateKeeper.error = {};
            $stateKeeper.error.status = response.status;
            if (response.data !== undefined && response.data.errorMsg !== undefined) {
                var str = response.data.errorMsg;
                $stateKeeper.error.msg = str;
                $log.log(str);
            } else {
                var str = response.statusText;
                $stateKeeper.error.msg = str;
                $log.log(str);
            }
        };

        obj.loginError = function (str) {
            $stateKeeper.error = {};
            $stateKeeper.error.status = str;
            $log.log(str);
        };

        return obj;
    });
})();