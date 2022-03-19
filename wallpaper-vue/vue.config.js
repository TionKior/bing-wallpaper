const webpack = require("webpack");
module.exports = {
    configureWebpack: {
        plugins: [
            new webpack.ProvidePlugin({
                // 设置公用变量
                $:'jquery',
                jQuery:'jquery',
                'window.jQuery':'jquery',
                Popper:['popper.js', 'default']
            })
        ]
    },
    runtimeCompiler: true,
    devServer: {
        hot: true,
        open: true,
        port: 8081,
        host: "127.0.0.1",
    },

}
