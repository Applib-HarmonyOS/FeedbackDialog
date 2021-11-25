package design.ivisionblog.apps.reviewdialoglibrary;

import design.ivisionblog.apps.reviewdialoglibrary.utils.LogUtil;

import ohos.agp.render.Texture;
import ohos.agp.render.Canvas;
import ohos.agp.render.BlendMode;
import ohos.agp.render.Paint;
import ohos.agp.render.PixelMapHolder;
import ohos.app.Context;

import ohos.agp.colors.RgbColor;
import ohos.agp.components.Image;
import ohos.agp.components.Text;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Component;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.text.Font;
import ohos.agp.utils.Color;
import ohos.agp.window.dialog.BaseDialog;
import ohos.agp.window.dialog.CommonDialog;
import ohos.agp.window.dialog.IDialog;

import ohos.global.resource.RawFileEntry;
import ohos.global.resource.Resource;
import ohos.global.resource.ResourceManager;
import ohos.global.resource.NotExistException;
import ohos.global.resource.WrongTypeException;

import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;
import ohos.media.image.common.PixelFormat;
import ohos.media.image.common.Size;

import java.io.IOException;

public class FeedBackDialog {
    private static final String TAG = "FeedbackDialog";
    private Context mContext;
    private int mIcon;
    private int mIconColor;
    private int mTitle;
    private int mBackgroundColor;
    private int mDescription;
    private int mReviewQuestion;
    private Image titleImageView;
    private Text titleTextView;
    private Text descriptionTextView;
    private Text reviewQuestionTextView;
    private DirectionalLayout positiveFeedbackLayout;
    private DirectionalLayout negativeFeedbackLayout;
    private DirectionalLayout ambiguityFeedbackLayout;
    private DirectionalLayout feedbackBodyLayout;
    private Text positiveFeedbackTextView;
    private Text negativeFeedbackTextView;
    private Text ambiguityFeedbackTextView;
    private int mPositiveFeedbackText;
    private int mPositiveFeedbackIcon;
    private int mNegativeFeedbackText;
    private int mNegativeFeedbackIcon;
    private int mAmbiguityFeedbackText;
    private int mAmbiguityFeedbackIcon;
    private Image positiveFeedbackIconView;
    private Image negativeFeedbackIconView;
    private Image ambiguityFeedbackIconView;
    private ShapeElement roundIconBackground;
    private ShapeElement feedBackground;
    private ComponentContainer rootContainer;
    private CommonDialog mDialog;
    private Font.Builder mTypeFace;
    private PixelMap srcPixelMap;
    private Texture mIconTexture;

    private FeedBackActionsListeners mReviewActionsListener;

    public FeedBackDialog(Context mContext) {
        this.mContext = mContext;

        mDialog = new CommonDialog(mContext);

        rootContainer = (ComponentContainer)
                LayoutScatter.getInstance(mContext).parse(ResourceTable.Layout_review_dialog_base, null, false);

        mDialog.setContentCustomComponent(rootContainer);

        int width = (int) (mContext.getResourceManager().getDeviceCapability().width * 0.90);
        int height = (int) (mContext.getResourceManager().getDeviceCapability().height * 0.50);
        if (mDialog.getWindow() != null) {
            mDialog.getWindow().setWindowLayout(width, height);
        }

        mTypeFace = new Font.Builder("");
        mTypeFace.setWeight(Font.BOLD);
    }

    private void initiateAllViews() {
        titleImageView = (Image) mDialog.getContentCustomComponent().findComponentById(ResourceTable.Id_review_icon);
        titleTextView  = (Text) mDialog.getContentCustomComponent().findComponentById(ResourceTable.Id_review_title);
        descriptionTextView  = (Text) mDialog.getContentCustomComponent().findComponentById(ResourceTable.Id_review_description);
        reviewQuestionTextView = (Text) mDialog.getContentCustomComponent().findComponentById(ResourceTable.Id_review_questions);
        feedbackBodyLayout = (DirectionalLayout) mDialog.getContentCustomComponent().findComponentById(ResourceTable.Id_feedback_body_layout);
        positiveFeedbackLayout = (DirectionalLayout) mDialog.getContentCustomComponent().findComponentById(ResourceTable.Id_postive_feedback_layout);
        negativeFeedbackLayout = (DirectionalLayout) mDialog.getContentCustomComponent().findComponentById(ResourceTable.Id_negative_feedback_layout);
        ambiguityFeedbackLayout = (DirectionalLayout) mDialog.getContentCustomComponent().findComponentById(ResourceTable.Id_ambiguity_feedback_layout);
        positiveFeedbackTextView = (Text) mDialog.getContentCustomComponent().findComponentById(ResourceTable.Id_positive_feedback_text);
        negativeFeedbackTextView = (Text) mDialog.getContentCustomComponent().findComponentById(ResourceTable.Id_negative_feedback_text);
        ambiguityFeedbackTextView = (Text) mDialog.getContentCustomComponent().findComponentById(ResourceTable.Id_ambiguity_feedback_text);
        positiveFeedbackIconView = (Image) mDialog.getContentCustomComponent().findComponentById(ResourceTable.Id_postive_feedback_icon);
        negativeFeedbackIconView = (Image) mDialog.getContentCustomComponent().findComponentById(ResourceTable.Id_negative_feedback_icon);
        ambiguityFeedbackIconView = (Image) mDialog.getContentCustomComponent().findComponentById(ResourceTable.Id_ambiguity_feedback_icon);
    }

    private void initiateListeners() {
        positiveFeedbackLayout.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                onPositiveFeedbackClicked(component);
            }
        });

        negativeFeedbackLayout.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                onNegativeFeedbackClicked(component);
            }
        });

        ambiguityFeedbackLayout.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                onAmbiguityFeedbackClicked(component);
            }
        });

        if(mDialog != null) {
            mDialog.registerRemoveCallback(new BaseDialog.RemoveCallback() {
                @Override
                public void onRemove(IDialog dialog) {
                    onCancelListener(dialog);
                }
            });
        }
    }

    private static PixelMap getPixelMap(Context context, int id) {
        String path = getPathById(context, id);
        RawFileEntry assetManager = context.getResourceManager().getRawFileEntry(path);
        ImageSource.SourceOptions options = new ImageSource.SourceOptions();
        options.formatHint = "image/png";
        ImageSource.DecodingOptions decodingOptions = new ImageSource.DecodingOptions();

        Resource asset = null;
        try {
            asset = assetManager.openRawFile();
        } catch (IOException e) {
            LogUtil.debug(TAG, e.getMessage());
        }
        ImageSource source = ImageSource.create(asset, options);
        return source.createPixelmap(decodingOptions);
    }

    private static String getPathById(Context context, int id) {
        String path = "";
        if(context == null) {
            return path;
        }

        ResourceManager manager = context.getResourceManager();
        if(manager == null) {
            return path;
        }
        try {
            path = manager.getMediaPath(id);
        } catch (IOException  | NotExistException  | WrongTypeException e) {
             LogUtil.debug(TAG, e.getMessage());
        }
        return path;
    }

    private Texture setIconColor(PixelMap srcPixelMap) {
        PixelMap.InitializationOptions initializationOptions = new PixelMap.InitializationOptions();

        initializationOptions.size = new Size(srcPixelMap.getImageInfo().size.width,
                srcPixelMap.getImageInfo().size.height);

        initializationOptions.pixelFormat = PixelFormat.ARGB_8888;

        PixelMap colorSrc = PixelMap.create(initializationOptions);
        Texture maskTexture = new Texture(colorSrc);
        Canvas maskCanvas = new Canvas(maskTexture);

        try {
            maskCanvas.drawColor(mContext.getResourceManager().getElement(this.mIconColor).getColor(), BlendMode.SRC);
        } catch(IOException | NotExistException | WrongTypeException e) {
            LogUtil.debug(TAG, e.getMessage());
        }

        PixelMap colorDestination = maskTexture.getPixelMap();
        PixelMap result = PixelMap.create(initializationOptions);
        Texture texture = new Texture(result);
        Canvas mCanvas = new Canvas(texture);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setBlendMode(BlendMode.DST_IN);
        mCanvas.drawPixelMapHolder(new PixelMapHolder(colorDestination), 0, 0 ,new Paint());
        mCanvas.drawPixelMapHolder(new PixelMapHolder(srcPixelMap), 0, 0 , paint);

        return texture;
    }

    public FeedBackDialog show() {
        if(mDialog != null && mContext != null) {
            initiateAllViews();
            initiateListeners();

            roundIconBackground = new ShapeElement();
            roundIconBackground.setShape(ShapeElement.OVAL);
            roundIconBackground.setRgbColor(RgbColor.fromArgbInt(Color.WHITE.getValue()));

            titleTextView.setText(mContext.getString(this.mTitle));
            titleTextView.setFont(mTypeFace.build());

            if(mIcon != 0) {
                srcPixelMap = getPixelMap(mContext, mIcon);
                mIconTexture = setIconColor(srcPixelMap);

                titleImageView.setScaleMode(Image.ScaleMode.INSIDE);
                titleImageView.setPixelMap(mIconTexture.getPixelMap());
            }
            titleImageView.setBackground(roundIconBackground);

            descriptionTextView.setText(mContext.getString(this.mDescription));
            descriptionTextView.setFont(mTypeFace.build());

            reviewQuestionTextView.setText(mContext.getString(this.mReviewQuestion));
            reviewQuestionTextView.setFont(mTypeFace.build());

            positiveFeedbackTextView.setText(this.mPositiveFeedbackText);
            if(this.mPositiveFeedbackIcon != 0) {
                srcPixelMap = getPixelMap(mContext, this.mPositiveFeedbackIcon);
                mIconTexture = setIconColor(srcPixelMap);

                positiveFeedbackIconView.setScaleMode(Image.ScaleMode.INSIDE);
                positiveFeedbackIconView.setPixelMap(mIconTexture.getPixelMap());
            }

            negativeFeedbackTextView.setText(this.mNegativeFeedbackText);
            if(this.mNegativeFeedbackIcon != 0) {
                srcPixelMap = getPixelMap(mContext, this.mNegativeFeedbackIcon);
                mIconTexture = setIconColor(srcPixelMap);

                negativeFeedbackIconView.setScaleMode(Image.ScaleMode.INSIDE);
                negativeFeedbackIconView.setPixelMap(mIconTexture.getPixelMap());
            }

            ambiguityFeedbackTextView.setText(this.mAmbiguityFeedbackText);
            if(this.mAmbiguityFeedbackIcon != 0) {
                srcPixelMap = getPixelMap(mContext, this.mAmbiguityFeedbackIcon);
                mIconTexture = setIconColor(srcPixelMap);

                ambiguityFeedbackIconView.setScaleMode(Image.ScaleMode.INSIDE);
                ambiguityFeedbackIconView.setPixelMap(mIconTexture.getPixelMap());
            }

            feedBackground = new ShapeElement();
            try {
                feedBackground.setRgbColor(RgbColor.fromArgbInt(mContext.getResourceManager().getElement(this.mBackgroundColor).getColor()));
            } catch(IOException | NotExistException | WrongTypeException e) {
                LogUtil.debug(TAG, e.getMessage());
            }

            feedbackBodyLayout.setBackground(feedBackground);

            mDialog.show();
        }
        return this;
    }

    public int getTitleIcon() {
        return mIcon;
    }

    public FeedBackDialog setIcon(int mIcon) {
        this.mIcon =  mIcon;
        return this;
    }

    public int getTitle() {
        return mTitle;
    }

    public FeedBackDialog setTitle(int mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public int getDescription() {
        return mDescription;
    }

    public FeedBackDialog setDescription(int mDescription) {
        this.mDescription = mDescription;
        return this;
    }

    public int getPositiveFeedbackText() {
        return mPositiveFeedbackText;
    }

    public FeedBackDialog setPositiveFeedbackText(int mPositiveFeedbackText) {
        this.mPositiveFeedbackText = mPositiveFeedbackText;
        return this;
    }

    public int getPositiveFeedbackIcon() {
        return mPositiveFeedbackIcon;
    }

    public FeedBackDialog setPositiveFeedbackIcon(int mPositiveFeedbackIcon) {
        this.mPositiveFeedbackIcon = mPositiveFeedbackIcon;
        return this;
    }

    public int getNegativeFeedbackText() {
        return mNegativeFeedbackText;
    }

    public FeedBackDialog setNegativeFeedbackText(int mNegativeFeedbackText) {
        this.mNegativeFeedbackText = mNegativeFeedbackText;
        return this;
    }

    public int getNegativeFeedbackIcon() {
        return mNegativeFeedbackIcon;
    }

    public FeedBackDialog setNegativeFeedbackIcon(int mNegativeFeedbackIcon) {
        this.mNegativeFeedbackIcon = mNegativeFeedbackIcon;
        return this;
    }

    public int getAmbiguityFeedbackText() {
        return mAmbiguityFeedbackText;
    }

    public FeedBackDialog setAmbiguityFeedbackText(int mAmbiguityFeedbackText) {
        this.mAmbiguityFeedbackText = mAmbiguityFeedbackText;
        return this;
    }

    public int getAmbiguityFeedbackIcon() {
        return mAmbiguityFeedbackIcon;
    }

    public FeedBackDialog setAmbiguityFeedbackIcon(int mAmbiguityFeedbackIcon) {
        this.mAmbiguityFeedbackIcon = mAmbiguityFeedbackIcon;
        return this;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public FeedBackDialog setBackgroundColor(int mBackgroundColor) {
        this.mBackgroundColor = mBackgroundColor;
        return this;
    }

    public int getIconColor() {
        return mIconColor;
    }

    public FeedBackDialog setIconColor(int mIconColor) {
        this.mIconColor = mIconColor;
        return this;
    }

    public int getReviewQuestion() {
        return mReviewQuestion;
    }

    public FeedBackDialog setReviewQuestion(int mReviewQuestion) {
        this.mReviewQuestion = mReviewQuestion;
        return this;
    }

    public FeedBackDialog setOnReviewClickListener(FeedBackActionsListeners reviewActionsListeners) {
        this.mReviewActionsListener = reviewActionsListeners;
        return this;
    }

    public void dismiss() {
        if(mDialog != null) {
            mDialog.destroy();
        }
    }

    private void onPositiveFeedbackClicked(Component view) {
        if(mReviewActionsListener != null) {
            mReviewActionsListener.onPositiveFeedback(this);
        }
    }

    private void onNegativeFeedbackClicked(Component view) {
        if(mReviewActionsListener != null) {
            mReviewActionsListener.onNegativeFeedback(this);
        }
    }

    private void onAmbiguityFeedbackClicked(Component view) {
        if(mReviewActionsListener != null) {
            mReviewActionsListener.onAmbiguityFeedback(this);
        }
    }

    private void onCancelListener(IDialog dialog) {
        if (mReviewActionsListener != null) {
            mReviewActionsListener.onCancelListener(dialog);
        }
    }
}
