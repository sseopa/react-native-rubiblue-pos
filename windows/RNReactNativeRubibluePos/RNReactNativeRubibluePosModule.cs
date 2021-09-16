using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace React.Native.Rubiblue.Pos.RNReactNativeRubibluePos
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNReactNativeRubibluePosModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNReactNativeRubibluePosModule"/>.
        /// </summary>
        internal RNReactNativeRubibluePosModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNReactNativeRubibluePos";
            }
        }
    }
}
