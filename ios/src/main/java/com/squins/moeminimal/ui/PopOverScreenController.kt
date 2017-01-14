package com.squins.moeminimal.ui

import apple.coregraphics.c.CoreGraphics.CGPointZero
import apple.coregraphics.struct.CGRect
import apple.uikit.UIColor
import apple.uikit.UILabel
import apple.uikit.UIView
import apple.uikit.UIViewController
import com.squins.cazander.ios.ui.layout.*
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

        val view = UIView.alloc().initWithFrame(CGRect(CGPointZero(), Main.window.frame().size())).apply {
            setBackgroundColor(UIColor.blackColor().colorWithAlphaComponent(0.8))
        }
        setView(view)


        val contentView = createContentView()
        view.addSubview(contentView)
        contentView.centerY()
        contentView.centerX()
    }


    private fun createContentView(): UIView {
        val windowSize = Main.window.bounds().size()

        return UIView.alloc().init().apply {
            setBackgroundColor(UIColor.brownColor())

            height((windowSize.height() - (POPOVER_MARGIN * 2)).toInt())
            width((windowSize.width() - (POPOVER_MARGIN * 2)).toInt())

            val firstLabel = firstLabelAutoLayout()
            addSubview(firstLabel)
            firstLabel.centerX()

            firstLabel.alignTopToParentTopWithMinHeight(50)

            val secondLabel = secondLabel()
            addSubview(secondLabel)

            secondLabel.centerX()
            secondLabel.alignTopToBottomOf(firstLabel, 15)
            setClipsToBounds(true)

            setTranslatesAutoresizingMaskIntoConstraints(false)
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
        return UILabel.alloc().init().apply {
            setText("Second label")
            setBackgroundColor(UIColor.greenColor())
            setTranslatesAutoresizingMaskIntoConstraints(false)
        }

    }

    companion object {

        @Owned
        @Selector("alloc")
        @JvmStatic external fun alloc(): PopOverScreenController
    }
}
