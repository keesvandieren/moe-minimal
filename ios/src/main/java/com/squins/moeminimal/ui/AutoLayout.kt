package com.squins.cazander.ios.ui.layout

import apple.uikit.NSLayoutConstraint.constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant
import apple.uikit.UIView
import apple.uikit.enums.NSLayoutAttribute.*
import apple.uikit.enums.NSLayoutRelation.*

fun UIView.centerX() {
    assertHasSuperView()

    this.superview().addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, CenterX, Equal, this.superview(), CenterX, 1.0, 0.0))
}

fun UIView.centerY() {
    assertHasSuperView()

    this.superview().addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, CenterY, Equal, this.superview(), CenterY, 1.0, 0.0))
}

fun UIView.alignTopToParentTop() {
    assertHasSuperView()

    this.superview().addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, Top, Equal, this.superview(), Top, 1.0, 0.0))
}

fun UIView.alignTopToParentTopWithMinHeight(minHeight: Int) {
    assertHasSuperView()

    this.superview().addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, Top, GreaterThanOrEqual, this.superview(), Top, 1.0, minHeight.toDouble()))
}

fun UIView.alignTopToBottomOf(other: UIView) {
    alignTopToBottomOf(other, 0)
}

fun UIView.alignTopToBottomOf(other: UIView, margin: Int) {
    assertHasSuperView()

    this.superview().addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, Top, Equal, other, Bottom, 1.0, margin.toDouble()))
}

fun UIView.alignLeadingToParentLeading() {
    assertHasSuperView()

    this.superview().addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, Leading, Equal, this.superview(), Leading, 1.0, 0.0))
}

fun UIView.alignLeadingToParentLeadingWithMinWidth(minWidth: Int) {
    assertHasSuperView()

    this.superview().addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, Leading, GreaterThanOrEqual, this.superview(), Leading, 1.0, minWidth.toDouble()))
}

fun UIView.alignLeadingToTrailingOf(other: UIView) {
    assertHasSuperView()

    this.superview().addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, Leading, Equal, other, Trailing, 1.0, 0.0))
}

fun UIView.alignTrailingToParentTrailing() {
    assertHasSuperView()

    this.superview().addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, Trailing, Equal, this.superview(), Trailing, 1.0, 0.0))
}

fun UIView.alignTrailingToParentTrailingWithMinWidth(minWidth: Int) {
    assertHasSuperView()

    this.superview().addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, Trailing, GreaterThanOrEqual, this.superview(), Trailing, 1.0, minWidth.toDouble()))
}

fun UIView.alignBottomToParentBottom() {
    alignBottomToParentBottom(0)
}

fun UIView.alignBottomToParentBottom(margingHeight: Int) {
    assertHasSuperView()

    this.superview().addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, Bottom, Equal, this.superview(), Bottom, 1.0, -margingHeight.toDouble()))
}

fun UIView.alignBottomToParentBottomWithMinHeight(minHeight: Int) {
    assertHasSuperView()

    this.superview().addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, Bottom, LessThanOrEqual, this.superview(), Bottom, 1.0, -minHeight.toDouble()))
}

fun UIView.alignBottomToTopOf(other: UIView) {
    assertHasSuperView()

    this.superview().addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, Bottom, Equal, other, Top, 1.0, 0.0))
}

fun UIView.alignAllSidesToParentSides() {
    alignTopToParentTop()
    alignLeadingToParentLeading()
    alignTrailingToParentTrailing()
    alignBottomToParentBottom()
}

// TODO: iOS coordinate system uses Double, refactor to double
fun UIView.width(width: Int) {
    removeExistingConstraintIfNeeded(Width)

    this.addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, Width, Equal, null, NotAnAttribute, 1.0, width.toDouble()))
}

// TODO: iOS coordinate system uses Double, refactor to double
fun UIView.maxWidth(width: Int) {
    removeExistingConstraintIfNeeded(Width)

    this.addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, Width, LessThanOrEqual, null, NotAnAttribute, 1.0, width.toDouble()))
}

fun UIView.matchParentWidth() {
    assertHasSuperView()

    this.addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, Width, Equal, this.superview(), Width, 1.0, 0.0))
}

fun UIView.matchContentViewToScrollViewParentWIdth() {
    assertHasSuperView()

    val scrollViewParent = this.superview().superview()
    scrollViewParent.addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, Width, Equal, scrollViewParent, Width, 1.0, 0.0))
}

/**
 * @param heightOrWidth  `apple.uikit.enums.NSLayoutAttribute.Height` or `apple.uikit.enums.NSLayoutAttribute.Width`
 */
private fun UIView.removeExistingConstraintIfNeeded(heightOrWidth: Long) {

    constraints().filter { constraint ->
        constraint.firstItem() == this
                && constraint.firstAttribute() == heightOrWidth
                && constraint.relation() == Equal &&
                constraint.multiplier() == 1.0
    }.forEach {
        removeConstraint(it)
    }
}

// TODO: iOS coordinate system uses Double, refactor to double
fun UIView.height(height: Int) {
    removeExistingConstraintIfNeeded(Height)

    this.addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, Height, Equal, null, NotAnAttribute, 1.0, height.toDouble()))
}

fun UIView.minHeight(height: Int) {
    removeExistingConstraintIfNeeded(Height)

    this.addConstraint(constraintWithItemAttributeRelatedByToItemAttributeMultiplierConstant(this, Height, GreaterThanOrEqual, null, NotAnAttribute, 1.0, height.toDouble()))
}

private fun UIView.assertHasSuperView() {
    checkNotNull(superview(), { "Constraints cannot be added before the view has been added to a super view." })
}
