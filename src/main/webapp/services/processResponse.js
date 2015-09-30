(function() {
    /**
     * Config
     */
    var moduleName = 'com.github.jlgrock.informatix.workmanager';
    module = angular.module(moduleName);

    module.factory('processResponse', function ($log, stateKeeper) {
        var obj = {};

        obj.processErrorResponse = function (response) {
            stateKeeper.clearAll();
            stateKeeper.error.status = response.status;
            if (response.data !== undefined && response.data.errorMsg !== undefined) {
                var str = response.data.errorMsg;
                stateKeeper.error.msg = str;
                $log.log(str);
            } else {
                var str = response.statusText;
                stateKeeper.error.msg = str;
                $log.log(str);
            }
        };

        obj.printError = function (str) {
            stateKeeper.clearAll();
            stateKeeper.error.msg = str;
            $log.log(str);
        };

        return obj;
    });
})();