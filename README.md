# FeedbackDialog

An Interactive Feedback Dialog inspired from Google Maps Review section

<img src="./screenshots/screenshot1.png"  width="15%" height="30%"></img>

## FeedbackDialog includes:
this library solution can help developers display an interactive feedback dialog

## Usage Instructions
1. A sample project which provides runnable code examples that demonstrate uses of the classes in this project is available in the demo/ folder.

2. The following core classes are the essentials interface toFeedbackDialog:
FeedBackDialog: In this class we are basically creating custom feedback dialog.

3. Java file changes

FeedBackDialog mDialog = new FeedBackDialog(MainAbilitySlice.this)
                        .setBackgroundColor(ResourceTable.Color_sampleColor)
                        .setIcon(ResourceTable.Media_screenshot1)
                        .setIconColor(ResourceTable.Color_sampleColor)
                        .setTitle(ResourceTable.String_brand_name)
                        .setDescription(ResourceTable.String_brand_description)
                        .setReviewQuestion(ResourceTable.String_customer_review_questions)
                        .setPositiveFeedbackText(ResourceTable.String_positive_feedback_text)
                        .setNegativeFeedbackText(ResourceTable.String_negative_feedback_text)
                        .setAmbiguityFeedbackText(ResourceTable.String_ambiguity_feedback_text)
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
                        
1. ```setIcon(int mIcon)``` - set Media Image at top of the Dialog as Icon. <br />
2. ```setTitle(int mTitle)``` -  set Title or Brand Name for the Feedback Dialog
3. ```setDescription(int mDescription)``` - set Description or Additional text for Feedback Dialog
4. ```setPositiveFeedbackText(int mPositiveFeedbackText)``` - set Positive Feedback button text
5. ```setNegativeFeedbackText(int mNegativeFeedbackText)``` - set Negative Feedback button text
6. ```setAmbiguityFeedbackText(int mAmbiguityFeedbackText``` - set Ambiguity Feedback button text
7. ```setPositiveFeedbackIcon(int mPositiveFeedbackIcon)``` - set Positive Feedback button Icon as element resource
8. ```setNegativeFeedbackIcon(int mNegativeFeedbackIcon)``` - set Negative Feedback button Icon as element resource
9. ```setAmbiguityFeedbackIcon(int mAmbiguityFeedbackIcon)``` - set Ambiguity Feedback button Icon as element resource
10. ```setBackgroundColor(int mBackgroundColor)``` - set Feedback Dialog background color
11. ```setIconColor(int mIconColor)``` - set Title and Action Icon colors
12. ```setReviewQuestion(int mReviewQuestion)``` - set Questionable message to end-user which is shown in Dialog
14. ```setOnReviewClickListener(FeedBackActionsListeners reviewActionsListeners)``` - set Feedback Action listeners which implements action listeners callback.
15. ```destroy()``` - destroy the active Feedback Dialog

### Installation instruction

1. For using module in demo app, include the below library dependency to generate hap/FeedbackDialog.har

        dependencies {
               implementation project(path: ':FeedbackDialog')
          }

2. Using the FeedbackDialog har, make sure to add FeedbackDialog.har file in the entry/libs folder and add the below dependency
in build.gradle

        dependencies {
               implementation fileTree(dir: 'libs', include: ['*.jar', '*.har'])
          }
    
## License
    Copyright 2015 the original author or authors.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
