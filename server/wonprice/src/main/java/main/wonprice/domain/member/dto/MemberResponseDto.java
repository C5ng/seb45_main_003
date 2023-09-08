package main.wonprice.domain.member.dto;

import lombok.Getter;
import lombok.Setter;
import main.wonprice.domain.member.entity.LoginType;
import main.wonprice.domain.member.entity.MemberStatus;
import main.wonprice.domain.picture.entity.MemberPicture;

@Getter @Setter
public class MemberResponseDto {

    private Long memberId;

    private String name;

    private String email;

    private String phone;

    private int postCount;

    private int tradeCount;

    private MemberPicture picture;
}
