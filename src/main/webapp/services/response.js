app.factory('responseService', function() {
    var obj = {};

    obj.processErrorResponse = function (scope, response) {
        scope.errors = true;
        scope.status = response.status;
        if (response.data !== undefined && response.data.errorMsg !== undefined) {
            scope.errorMessage = response.data.errorMsg;
        } else {
            scope.errorMessage = response.statusText;
        }
    };

    obj.loginError = function(scope, str) {
        scope.errorMessage = str;
        console.log(str);
        scope.errors = true;
    };

    return obj;
});