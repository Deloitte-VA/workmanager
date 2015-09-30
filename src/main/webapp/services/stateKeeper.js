(function() {
    /**
     * Config
     */
    var moduleName = 'com.github.jlgrock.informatix.workmanager';
    module = angular.module(moduleName);

    module.service('stateKeeper', function () {
        var error = { msg: "bla", status: 200 };// dummy error message that should be cleared by the developer at page load

        var obj = {};
        obj.error = error;
        obj.clearAll = function() {
            delete error.msg;
            delete error.status;
        };
        return obj;
    });
})();