package com.tianya.leetcode;

import com.tianya.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author changwenbo
 * @date 2023/7/23 20:31
 */
@Slf4j
public class Main1 {
	public static void main(String[] args) {


		String s = "/loan/login/login,/auth/operator/login/login,/auth/openapi/authorization,/auth/user/sendSms,/auth/user,/base/callback/face/auth,/base/will/callback,/loan/contract/getContract,/loan/enterprise/getMaxApplyAmount/0101,/preloan/enterprise/v2/getBankInfo/0101,/auth/user/externalTokenLogin,/channel/baixin/credit/callBack,/channel/baixin/loan/callBack,/channel/baixin/quota/callBack,/channel/baixin/repayment/callBack,/base/aioutbound/select,/base/aioutbound,/base/aioutbound/selectActiveOrderStatus,/base/callback/test,/base/callback/test2,/channel/common/credit/callBack,/channel/common/loan/callBack,/channel/common/repayment/callBack,/base/wechat/receive,/base/wechat/accept,/base/wechat/code2OpenId,/base/wechat/get/userInfo,/base/wechat/authorized/userInfo,/loanprod/enter/pre/login/check,/auth/user/refreshToken,base/wechat/get/js/signature,/base/callback/enterprise/seal/authentication,/base/callback/enterprise/authentication,/loanprod/carPolicyContractSign/getProtocolInfo,/loanprod/carPolicyContractSign/signConfirm,/base/contract/carPolicySignFlowBackNotify,/loanprod/insurance/policy/order/queryOrderDetail,/base/aioutbound/selectIsContinueAICall,/base/wechat/code2UserInfo,/channel/common/limitRateChange/notice,/base/callback/aiOutbound/callOutResult";
		String[] split = s.split(",");
		System.out.println(GsonUtils.toJson(split));


	}

	// 2,7,11,15
	public int[] twoSum1(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[]{i, j};
				}
			}
		}

		return null;
	}


	// 2,7,11,15
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int x = nums[i];
			int y = target - x;
			if (map.containsKey(y)) {
				return new int[]{i, map.get(y)};
			}
			map.put(x, i);
		}



		return null;
	}
}


@Slf4j
class LogUtils {


	public static void log(Integer code, String format, Object... arguments) {
		if (code == null || needPrintErrorLog(code)) {
			error(format, arguments);
		} else {
			warn(format, arguments);
		}
	}

	public static void error(String format, Object... arguments) {
		log.error(format, arguments);
	}

	public static void warn(String format, Object... arguments) {
		log.warn(format, arguments);
	}

	public static void debug(String format, Object... arguments) {
		if (log.isDebugEnabled()) {
			log.debug(format, arguments);
		}
	}

	public static void info(String format, Object... arguments) {
		if (log.isInfoEnabled()) {
			log.info(format, arguments);
		}
	}

	public static boolean needPrintErrorLog(Integer code) {
		if (code == null) {
			return true;
		}

		// 5位数字，并且是3xxxx开头的code，都可以进行忽略
		if (code >= 30000 && code < 40000) {
			return true;
		}

		return false;
	}

}

