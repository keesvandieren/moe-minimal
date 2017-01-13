package com.squins.moeminimal.ui

import apple.coregraphics.c.CoreGraphics.CGPointZero
import apple.coregraphics.struct.CGPoint
import apple.coregraphics.struct.CGRect
import apple.coregraphics.struct.CGSize
import apple.uikit.UIColor
import apple.uikit.UILabel
import apple.uikit.UIView
import apple.uikit.UIViewController
import com.squins.cazander.ios.ui.layout.minHeight
import com.squins.cazander.ios.ui.layout.width
import com.squins.moeminimal.Main
import org.moe.natj.general.Pointer
import org.moe.natj.general.ann.Owned
import org.moe.natj.objc.ann.Selector


class PopOverScreenController protected constructor(peer: Pointer) : UIViewController(peer) {

    private val POPOVER_MARGIN = 30.0

    @Selector("init")
    override external fun init(): PopOverScreenController


    override fun viewDidLoad() {
        super.viewDidLoad()

        println("Preferred size: " + preferredContentSize())
        val view = UIView.alloc().initWithFrame(CGRect(CGPointZero(), Main.window.frame().size())).apply {
            setBackgroundColor(UIColor.blackColor().colorWithAlphaComponent(0.2))
        }
        setView(view)


        val contentView = createContentView()
        view.addSubview(contentView)

    }


    private fun createContentView(): UIView {
        val windowSize = Main.window.bounds().size()

        return UIView.alloc().initWithFrame(CGRect(CGPoint(POPOVER_MARGIN, POPOVER_MARGIN),
                CGSize(windowSize.width() - (POPOVER_MARGIN * 2), windowSize.height() - (POPOVER_MARGIN * 2)))).apply {
            setBackgroundColor(UIColor.brownColor())

            val firstLabel = firstLabelManualLayout()  // firstLabelAutoLayout breaks everything: setTranslatesAutoresizingMaskIntoConstraints() stucks everything.
            addSubview(firstLabel)
//
//            val secondLabel = secondLabel()
//            addSubview(secondLabel)
//
//            secondLabel.alignTopToBottomOf(firstLabel, 15)
//            setClipsToBounds(true)
//
            setTranslatesAutoresizingMaskIntoConstraints(false)
        }
    }

    private fun firstLabelManualLayout(): UIView {
        return UILabel.alloc().initWithFrame(CGRect(CGPointZero(), CGSize(100.0, 100.0))).apply {
            setText("First label")
            setBackgroundColor(UIColor.redColor())
        }

    }

    private fun firstLabelAutoLayout(): UIView {
        return UILabel.alloc().init().apply {
            setText("First label")
            setBackgroundColor(UIColor.redColor())
            setTranslatesAutoresizingMaskIntoConstraints(false)
        }

    }


    private fun secondLabel(): UIView {
        return UILabel.alloc().initWithFrame(CGRect(CGPointZero(), CGSize(100.0, 100.0))).apply {
            setText("Second label")
            setBackgroundColor(UIColor.redColor())
            setTranslatesAutoresizingMaskIntoConstraints(false)
            minHeight(25)
            width(300)
        }

    }

    companion object {

        @Owned
        @Selector("alloc")
        @JvmStatic external fun alloc(): PopOverScreenController
    }
}
