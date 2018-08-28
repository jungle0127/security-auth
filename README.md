# security-auth
# 3rd party encryption library:
http://www.jasypt.org/features.html

#Authentication Interface of Spring Security

封装了认证信息，
认证信息包括：
发起的认证请求里面的一些信息，认证请求的IP是多少，session是什么，认证完成以后的用户信息。

{
	"authorities": [
		{
			"authority": "admin"
		}
	],
	"details": {
		"remoteAddress": "0:0:0:0:0:0:0:1",
		"sessionId": "58E5B4AF1A95B720444475D148417273"
	},
	"authenticated": true,
	"principal": {
		"password": null,
		"username": "un",
		"authorities": [
			{
				"authority": "admin"
			}
		],
		"accountNonExpired": true,
		"accountNonLocked": true,
		"credentialsNonExpired": true,
		"enabled": true
	},
	"credentials": null,
	"name": "un"
}

# HTTP content type:
MIME (Multipurpose Internet Mail Extensions) 是描述消息内容类型的因特网标准。 

# authentication
