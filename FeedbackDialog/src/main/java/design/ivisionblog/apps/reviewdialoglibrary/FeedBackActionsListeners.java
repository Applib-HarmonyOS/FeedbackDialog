package design.ivisionblog.apps.reviewdialoglibrary;

import ohos.agp.window.dialog.BaseDialog;
import ohos.agp.window.dialog.CommonDialog;
import ohos.agp.window.dialog.IDialog;

public interface FeedBackActionsListeners
{
    void onPositiveFeedback(FeedBackDialog dialog);
    void onNegativeFeedback(FeedBackDialog dialog);
    void onAmbiguityFeedback(FeedBackDialog dialog);
    void onCancelListener(IDialog dialog);
}
