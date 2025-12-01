package org.wso2.carbon.user.mgt.workflow.rule;

import org.wso2.carbon.identity.rule.evaluation.api.exception.RuleEvaluationDataProviderException;
import org.wso2.carbon.identity.rule.evaluation.api.model.Field;
import org.wso2.carbon.identity.rule.evaluation.api.model.FieldValue;
import org.wso2.carbon.identity.rule.evaluation.api.model.FlowContext;
import org.wso2.carbon.identity.rule.evaluation.api.model.FlowType;
import org.wso2.carbon.identity.rule.evaluation.api.model.RuleEvaluationContext;
import org.wso2.carbon.identity.rule.evaluation.api.model.ValueType;
import org.wso2.carbon.identity.rule.evaluation.api.provider.RuleEvaluationDataProvider;

import java.util.ArrayList;
import java.util.List;

public class PreUpdateUserListOfRoleRuleEvaluationDataProvider implements RuleEvaluationDataProvider {

    @Override
    public FlowType getSupportedFlowType() {
        return FlowType.PRE_UPDATE_USER_LIST_OF_ROLE;
    }

    @Override
    public List<FieldValue> getEvaluationData(RuleEvaluationContext ruleEvaluationContext,
                                              FlowContext flowContext,
                                              String tenantDomain) throws RuleEvaluationDataProviderException {

        List<FieldValue> fieldValues = new ArrayList<>();

        // Retrieve the raw map we passed from the Listener
        Object rawGrantType = flowContext.getContextData().get("grantType");

        // Loop through fields the Rule expects (e.g., "grantType")
        for (Field field : ruleEvaluationContext.getFields()) {
            if ("grantType".equals(field.getName())) {
                // Map the raw data to a FieldValue
                fieldValues.add(new FieldValue("grantType",
                        rawGrantType != null ? rawGrantType.toString() : "",
                        ValueType.STRING));
            }
        }

        return fieldValues;
    }
}