package com.squins.moeminimal.ui;

import org.moe.natj.general.Pointer;
import org.moe.natj.general.ann.Owned;
import org.moe.natj.general.ann.RegisterOnStartup;
import org.moe.natj.objc.ObjCRuntime;
import org.moe.natj.objc.ann.ObjCClassName;
import org.moe.natj.objc.ann.Property;
import org.moe.natj.objc.ann.Selector;

import apple.NSObject;
import apple.uikit.UIButton;
import apple.uikit.UILabel;
import apple.uikit.UIPopoverPresentationController;
import apple.uikit.UIPresentationController;
import apple.uikit.UIViewController;
import apple.uikit.enums.UIModalPresentationStyle;
import apple.uikit.protocol.UIPopoverPresentationControllerDelegate;

@org.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("AppViewController")
@RegisterOnStartup
public class AppViewController extends UIViewController {

    @Owned
    @Selector("alloc")
    public static native AppViewController alloc();

    @Selector("init")
    public native AppViewController init();

    protected AppViewController(Pointer peer) {
        super(peer);
    }

    public UILabel statusText = null;
    public UIButton helloButton = null;

    @Override
    public void viewDidLoad() {
        System.out.println("viewDidLoad()");
        statusText = getLabel();
        helloButton = getHelloButton();
    }

    @Selector("statusText")
    @Property
    public native UILabel getLabel();

    @Selector("helloButton")
    @Property
    public native UIButton getHelloButton();

    @Selector("BtnPressedCancel_helloButton:")
    public void BtnPressedCancel_button(NSObject sender) {
        statusText.setText("Hello Intel Multi-OS Engine!");

        UIViewController controller = PopOverScreenController.alloc().init();

        controller.setModalPresentationStyle(UIModalPresentationStyle.Popover);
        UIPopoverPresentationController popover = controller.popoverPresentationController();
        popover.setSourceView(this.view());
        popover.setDelegate(new UIPopoverPresentationControllerDelegate() {
            @Override
            public long adaptivePresentationStyleForPresentationController(UIPresentationController controller) {
                return UIModalPresentationStyle.OverFullScreen;
            }
        });

        presentViewControllerAnimatedCompletion(controller, false, null);
    }
}
