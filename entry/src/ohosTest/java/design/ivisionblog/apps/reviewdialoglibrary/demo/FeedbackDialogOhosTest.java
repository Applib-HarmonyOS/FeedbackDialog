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
package design.ivisionblog.apps.reviewdialoglibrary.demo;

import design.ivisionblog.apps.reviewdialoglibrary.FeedBackDialog;
import design.ivisionblog.apps.reviewdialoglibrary.demo.slice.MainAbilitySlice;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.app.Context;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class FeedbackDialogOhosTest {
    FeedBackDialog mFeedBackDialog;

    @Before
    public void setup() {
        mFeedBackDialog = new FeedBackDialog(getContext());
    }

    @Test
    /**
     * Test Package Name
     */
    public void testBundleName() {
        final String actualBundleName = AbilityDelegatorRegistry.getArguments().getTestBundleName();
        assertEquals("design.ivisionblog.apps.reviewdialoglibrary.demo", actualBundleName);
    }

    /**
     * Test Set Background Color Not Null
     */
    @Test
    public void testSetBackgroundColorNotNull() {
        assertNotNull(mFeedBackDialog.setBackgroundColor(ResourceTable.Color_sampleColor));
    }

    /**
     * Test Set Background Color Null
     */
    @Test
    public void testSetBackgroundColorNull() {
        assertNull(mFeedBackDialog.setBackgroundColor(0));
    }

    /**
     * Test Set Icon Not Null
     */
    @Test
    public void testSetIconNotNull() {
        assertNotNull(mFeedBackDialog.setIcon(ResourceTable.Media_reviewdialog_ic_restaurant));
    }

    /**
     * Test Set Icon Null
     */
    @Test
    public void testSetIconNull() {
        assertNull(mFeedBackDialog.setIcon(0));
    }

    /**
     * Test Set Icon Color Not Null
     */
    @Test
    public void testSetIconColorNotNull() {
        assertNotNull(mFeedBackDialog.setIconColor(ResourceTable.Color_sampleColor));
    }

    /**
     * Test Set Icon Color Null
     */
    @Test
    public void testSetIconColorNull() {
        assertNull(mFeedBackDialog.setIconColor(0));
    }

    /**
     * Test Set Title Not Null
     */
    @Test
    public void testSetTitleNotNull() {
        assertNotNull(mFeedBackDialog.setTitle(ResourceTable.String_brand_name));
    }

    /**
     * Test Set Title Null
     */
    @Test
    public void testSetTitleNull() {
        assertNull(mFeedBackDialog.setTitle(0));
    }

    /**
     * Test Set Description Not Null
     */
    @Test
    public void testSetDescriptionNotNull() {
        assertNotNull(mFeedBackDialog.setDescription(ResourceTable.String_brand_description));
    }

    /**
     * Test Set Description Null
     */
    @Test
    public void testSetDescriptionNull() {
        assertNull(mFeedBackDialog.setDescription(0));
    }

    /**
     * Test Set Review Question Not Null
     */
    @Test
    public void testSetReviewQuestionNotNull() {
        assertNotNull(mFeedBackDialog.setReviewQuestion(ResourceTable.String_customer_review_questions));
    }

    /**
     * Test Set Review Question Null
     */
    @Test
    public void testSetReviewQuestionNull() {
        assertNull(mFeedBackDialog.setReviewQuestion(0));
    }

    /**
     * Test Set Positive Feedback Text Not Null
     */
    @Test
    public void testSetPositiveFeedbackTextNotNull() {
        assertNotNull(mFeedBackDialog.setPositiveFeedbackText(ResourceTable.String_positive_feedback_text));
    }

    /**
     * Test Set Positive Feedback Text Null
     */
    @Test
    public void testSetPositiveFeedbackTextNull() {
        assertNull(mFeedBackDialog.setPositiveFeedbackText(0));
    }

    /**
     * Test Set Positive Feedback Icon Not Null
     */
    @Test
    public void testSetPositiveFeedbackIconNotNull() {
        assertNotNull(mFeedBackDialog.setPositiveFeedbackIcon(ResourceTable.Media_reviewdialog_ic_accept_action));
    }

    /**
     * Test Set Positive Feedback Icon Null
     */
    @Test
    public void testSetPositiveFeedbackIconNull() {
        assertNull(mFeedBackDialog.setPositiveFeedbackIcon(0));
    }

    /**
     * Test Set Negative Feedback Text Not Null
     */
    @Test
    public void testSetNegativeFeedbackTextNotNull() {
        assertNotNull(mFeedBackDialog.setNegativeFeedbackText(ResourceTable.String_negative_feedback_text));
    }

    /**
     * Test Set Negative Feedback Text Null
     */
    @Test
    public void testSetNegativeFeedbackTextNull() {
        assertNull(mFeedBackDialog.setNegativeFeedbackText(0));
    }

    /**
     * Test Set Negative Feedback Icon Not Null
     */
    @Test
    public void testSetNegativeFeedbackIconNotNull() {
        assertNotNull(mFeedBackDialog.setNegativeFeedbackIcon(ResourceTable.Media_reviewdialog_ic_cancel_action));
    }

    /**
     * Test Set Negative Feedback Icon Null
     */
    @Test
    public void testSetNegativeFeedbackIconNull() {
        assertNull(mFeedBackDialog.setNegativeFeedbackIcon(0));
    }

    /**
     * Test Set Ambiguity Feedback Text Not Null
     */
    @Test
    public void testSetAmbiguityFeedbackTextNotNull() {
        assertNotNull(mFeedBackDialog.setAmbiguityFeedbackText(ResourceTable.String_ambiguity_feedback_text));
    }

    /**
     * Test Set Ambiguity Feedback Text Null
     */
    @Test
    public void testSetAmbiguityFeedbackTextNull() {
        assertNull(mFeedBackDialog.setAmbiguityFeedbackText(0));
    }

    /**
     * Test Set Ambiguity Feedback Icon Not Null
     */
    @Test
    public void testSetAmbiguityFeedbackIconNotNull() {
        assertNotNull(mFeedBackDialog.setAmbiguityFeedbackIcon(ResourceTable.Media_reviewdialog_ic_ambiguity_action));
    }

    /**
     * Test Set Ambiguity Feedback Icon Null
     */
    @Test
    public void testSetAmbiguityFeedbackIconNull() {
        assertNull(mFeedBackDialog.setAmbiguityFeedbackIcon(0));
    }

    /**
     * Test Set OnReviewClickListener Null
     */
    @Test
    public void testSetOnReviewClickListenerNull() {
        assertNull(mFeedBackDialog.setOnReviewClickListener(null));
    }

    /**
     * @return The {@link Context} of the test project.
     */
    private Context getContext()
    {
        try
        {
            Method getTestContext = MainAbilitySlice.class.getMethod("getContext");
            return (Context) getTestContext.invoke(this);
        }
        catch (final Exception exception)
        {
            return null;
        }
    }
}