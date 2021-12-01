/*
 * Copyright (C) 2020-21 Application Library Engineering Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package design.ivisionblog.apps.reviewdialoglibrary.demo.slice;

import design.ivisionblog.apps.reviewdialoglibrary.FeedBackActionsListeners;
import design.ivisionblog.apps.reviewdialoglibrary.FeedBackDialog;
import design.ivisionblog.apps.reviewdialoglibrary.demo.ResourceTable;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;

import ohos.agp.colors.RgbColor;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.window.dialog.IDialog;

public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    private static final String TAG = "MainAbilitySlice";

    private Button mTravelFeedBackBtn;
    private Button mHotelFeedBackBtn;
    private Button mSoftwareFeedBackBtn;
    private Button mAppFeedBackBtn;
    private ShapeElement mBackgroundShape;
    private FeedBackDialog mFeedBackDialog;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        mTravelFeedBackBtn = (Button) findComponentById(ResourceTable.Id_travelFeedBackButton);
        mTravelFeedBackBtn.setClickedListener(this);

        mHotelFeedBackBtn = (Button) findComponentById(ResourceTable.Id_hotelFeedBackButton);
        mHotelFeedBackBtn.setClickedListener(this);

        mSoftwareFeedBackBtn = (Button) findComponentById(ResourceTable.Id_softwareFeedBackButton);
        mSoftwareFeedBackBtn.setClickedListener(this);

        mAppFeedBackBtn = (Button) findComponentById(ResourceTable.Id_appFeedBackButton);
        mAppFeedBackBtn.setClickedListener(this);

        mBackgroundShape = new ShapeElement();
        mBackgroundShape.setShape(ShapeElement.RECTANGLE);
        mBackgroundShape.setRgbColor(new RgbColor(0x808080DD));

        mTravelFeedBackBtn.setBackground(mBackgroundShape);
        mHotelFeedBackBtn.setBackground(mBackgroundShape);
        mSoftwareFeedBackBtn.setBackground(mBackgroundShape);
        mAppFeedBackBtn.setBackground(mBackgroundShape);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    public void onClick(Component component) {
        switch (component.getId()) {
            case ResourceTable.Id_travelFeedBackButton:
                initFeedbackDialog();
                showTravelFeedbackDialog();
                break;
            case ResourceTable.Id_hotelFeedBackButton:
                initFeedbackDialog();
                showHotelFeedbackDialog();
                break;
            case ResourceTable.Id_softwareFeedBackButton:
                initFeedbackDialog();
                showSoftwareFeedbackDialog();
                break;
            case ResourceTable.Id_appFeedBackButton:
                initFeedbackDialog();
                showAppFeedbackDialog();
                break;
        }
    }

    private void initFeedbackDialog() {
        mFeedBackDialog = new FeedBackDialog(MainAbilitySlice.this);
        mFeedBackDialog.setPositiveFeedbackText(ResourceTable.String_positive_feedback_text);
        mFeedBackDialog.setPositiveFeedbackIcon(ResourceTable.Media_reviewdialog_ic_accept_action);
        mFeedBackDialog.setNegativeFeedbackText(ResourceTable.String_negative_feedback_text);
        mFeedBackDialog.setNegativeFeedbackIcon(ResourceTable.Media_reviewdialog_ic_cancel_action);
        mFeedBackDialog.setAmbiguityFeedbackText(ResourceTable.String_ambiguity_feedback_text);
        mFeedBackDialog.setAmbiguityFeedbackIcon(ResourceTable.Media_reviewdialog_ic_ambiguity_action);
        mFeedBackDialog.setOnReviewClickListener(new FeedBackActionsListeners() {
            @Override
            public void onPositiveFeedback(FeedBackDialog dialog) {
                dialog.dismiss();
            }

            @Override
            public void onNegativeFeedback(FeedBackDialog dialog) {
                dialog.dismiss();
            }

            @Override
            public void onAmbiguityFeedback(FeedBackDialog dialog) {
                dialog.dismiss();
            }

            @Override
            public void onCancelListener(IDialog dialog) {
                dialog.destroy();
            }
        });
    }

    private void showTravelFeedbackDialog() {
        mFeedBackDialog.setBackgroundColor(ResourceTable.Color_sampleColor);
        mFeedBackDialog.setIcon(ResourceTable.Media_reviewdialog_ic_travel);
        mFeedBackDialog.setIconColor(ResourceTable.Color_sampleColor);
        mFeedBackDialog.setTitle(ResourceTable.String_brand_name);
        mFeedBackDialog.setDescription(ResourceTable.String_brand_description);
        mFeedBackDialog.setReviewQuestion(ResourceTable.String_customer_review_questions);
        mFeedBackDialog.show();
    }

    private void showHotelFeedbackDialog() {
        mFeedBackDialog.setBackgroundColor(ResourceTable.Color_sampleColor2);
        mFeedBackDialog.setIcon(ResourceTable.Media_reviewdialog_ic_restaurant);
        mFeedBackDialog.setIconColor(ResourceTable.Color_sampleColor2);
        mFeedBackDialog.setTitle(ResourceTable.String_hotel_feedback_title);
        mFeedBackDialog.setDescription(ResourceTable.String_hotel_feedback_description);
        mFeedBackDialog.setReviewQuestion(ResourceTable.String_hotel_feedback_review_questions);
        mFeedBackDialog.show();
    }

    private void showSoftwareFeedbackDialog() {
        mFeedBackDialog.setBackgroundColor(ResourceTable.Color_sampleColor3);
        mFeedBackDialog.setIcon(ResourceTable.Media_reviewdialog_ic_software);
        mFeedBackDialog.setIconColor(ResourceTable.Color_sampleColor3);
        mFeedBackDialog.setTitle(ResourceTable.String_software_feedback_title);
        mFeedBackDialog.setDescription(ResourceTable.String_software_feedback_description);
        mFeedBackDialog.setReviewQuestion(ResourceTable.String_software_feedback_review_questions);
        mFeedBackDialog.show();
    }

    private void showAppFeedbackDialog() {
        mFeedBackDialog.setBackgroundColor(ResourceTable.Color_sampleColor4);
        mFeedBackDialog.setIcon(ResourceTable.Media_reviewdialog_ic_app);
        mFeedBackDialog.setIconColor(ResourceTable.Color_sampleColor4);
        mFeedBackDialog.setTitle(ResourceTable.String_app_feedback_title);
        mFeedBackDialog.setDescription(ResourceTable.String_app_feedback_description);
        mFeedBackDialog.setReviewQuestion(ResourceTable.String_app_feedback_review_questions);
        mFeedBackDialog.show();
    }
}
