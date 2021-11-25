package design.ivisionblog.apps.reviewdialoglibrary.demo.slice;

import design.ivisionblog.apps.reviewdialoglibrary.FeedBackActionsListeners;
import design.ivisionblog.apps.reviewdialoglibrary.FeedBackDialog;
import design.ivisionblog.apps.reviewdialoglibrary.demo.ResourceTable;
import design.ivisionblog.apps.reviewdialoglibrary.utils.LogUtil;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;

import ohos.agp.colors.RgbColor;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.window.dialog.IDialog;

public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    private static final String TAG = "MainAbilitySlice";
    private Button btnFeedBackDialog;
    private ShapeElement background;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        btnFeedBackDialog = (Button) findComponentById(ResourceTable.Id_myFeedBackButton);
        btnFeedBackDialog.setClickedListener(this);

        background = new ShapeElement();
        background.setShape(ShapeElement.RECTANGLE);
        background.setRgbColor(new RgbColor(0x808080DD));
        btnFeedBackDialog.setBackground(background);
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
            case ResourceTable.Id_myFeedBackButton:
                FeedBackDialog mDialog = new FeedBackDialog(MainAbilitySlice.this)
                        .setBackgroundColor(ResourceTable.Color_sampleColor)
                        .setIcon(ResourceTable.Media_reviewdialog_ic_restaurant)
                        .setIconColor(ResourceTable.Color_sampleColor)
                        .setTitle(ResourceTable.String_brand_name)
                        .setDescription(ResourceTable.String_brand_description)
                        .setReviewQuestion(ResourceTable.String_customer_review_questions)
                        .setPositiveFeedbackText(ResourceTable.String_positive_feedback_text)
                        .setPositiveFeedbackIcon(ResourceTable.Media_reviewdialog_ic_accept_action)
                        .setNegativeFeedbackText(ResourceTable.String_negative_feedback_text)
                        .setNegativeFeedbackIcon(ResourceTable.Media_reviewdialog_ic_cancel_action)
                        .setAmbiguityFeedbackText(ResourceTable.String_ambiguity_feedback_text)
                        .setAmbiguityFeedbackIcon(ResourceTable.Media_reviewdialog_ic_ambiguity_action)
                        .setOnReviewClickListener(new FeedBackActionsListeners() {
                            @Override
                            public void onPositiveFeedback(FeedBackDialog dialog) {
                                LogUtil.debug(TAG, "positive feedback callback");
                                dialog.dismiss();
                            }

                            @Override
                            public void onNegativeFeedback(FeedBackDialog dialog) {
                                LogUtil.debug(TAG, "negative feedback callback");
                                dialog.dismiss();
                            }

                            @Override
                            public void onAmbiguityFeedback(FeedBackDialog dialog) {
                                LogUtil.debug(TAG, "ambiguity feedback callback");
                                dialog.dismiss();
                            }

                            @Override
                            public void onCancelListener(IDialog dialog) {
                                LogUtil.debug(TAG, "feedback dialog cancel listener callback");
                                dialog.destroy();
                            }
                        })
                        .show();  // Finally don't forget to call show()
                break;
        }
    }
}
