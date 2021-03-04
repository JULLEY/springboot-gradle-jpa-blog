package leo.programming.config.oauth;

import leo.programming.config.auth.PrincipalDetail;
import leo.programming.config.oauth.provider.FacebookUserInfo;
import leo.programming.config.oauth.provider.GoogleUserInfo;
import leo.programming.config.oauth.provider.NaverUserInfo;
import leo.programming.config.oauth.provider.Oauth2UserInfo;
import leo.programming.model.RoleType;
import leo.programming.model.User;
import leo.programming.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    // 구글로부터받은 userRequest 데이터에 대한 후처리 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("getAttributes : " + oAuth2User.getAttributes());

        Oauth2UserInfo oauth2UserInfo = null;
        if("google".equals(userRequest.getClientRegistration().getRegistrationId())){
            System.out.println("구글 로그인 요청");
            oauth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }else if("facebook".equals(userRequest.getClientRegistration().getRegistrationId())){
            System.out.println("페이스북 로그인 요청");
            oauth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        }else if("naver".equals(userRequest.getClientRegistration().getRegistrationId())){
            System.out.println("네이버 로그인 요청");
            oauth2UserInfo = new NaverUserInfo((Map<String, Object>) oAuth2User.getAttributes().get("response"));
        }else{
            System.out.println("로그인 요청 경로를 찾을 수 없음");
        }
        Optional<User> userOptional =
                userRepository.findByProviderAndProviderId(oauth2UserInfo.getProvider(), oauth2UserInfo.getProviderId());

        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            // user가 존재하면 update 해주기
            user.setEmail(oauth2UserInfo.getEmail());
            userRepository.save(user);
        } else {
            // user의 패스워드가 null이기 때문에 OAuth 유저는 일반적인 로그인을 할 수 없음.
            user = User.builder()
                    .username(oauth2UserInfo.getProvider() + "_" + oauth2UserInfo.getProviderId())
//                    .password(bCryptPasswordEncoder.encode("1234"))
                    .email(oauth2UserInfo.getEmail())
                    .role(RoleType.USER)
                    .provider(oauth2UserInfo.getProvider())
                    .providerId(oauth2UserInfo.getProviderId())
                    .build();
            userRepository.save(user);
        }

        return new PrincipalDetail(user, oAuth2User.getAttributes());
    }
}
