(function() {
    /**
     * Config
     */
    var moduleName = 'com.github.jlgrock.informatix.workmanager';
    module = angular.module(moduleName);

    module.factory('security', function ($rootScope, $http, $location) {

        return {
            authenticate: function (credentials, callback) {

                var postheaders = credentials ? {
                    authorization: "Basic "
                    + btoa(credentials.email + ":"
                        + credentials.password)
                } : {};

                var headers = {
                    headers: postheaders
                };

                $http.get('principal', headers).then(
                    function (response) {
                        if (response.data['name']) {
                            $rootScope.authenticated = true;
                        } else {
                            $rootScope.authenticated = false;
                        }
                        callback && callback($rootScope.authenticated);
                    }, function (response) {
                        $rootScope.authenticated = false;
                        if (angular.isDefined(callback)) {
                            callback && callback(false);
                        } else {
                            $location.path("/security");
                        }
                    }
                );

            }
        };

    });
})();