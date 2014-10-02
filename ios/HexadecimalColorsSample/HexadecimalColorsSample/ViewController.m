//
//  ViewController.m
//  HexadecimalColorsSample
//
//  Created by Igor Khrupin on 10/2/14.
//  Copyright (c) 2014 www.hrupin.com. All rights reserved.
//

#import "ViewController.h"
#import "Utils.h"

@interface ViewController ()
@property (weak, nonatomic) IBOutlet UILabel *label1;
@property (weak, nonatomic) IBOutlet UILabel *label2;
@property (weak, nonatomic) IBOutlet UILabel *label3;
@property (weak, nonatomic) IBOutlet UILabel *label4;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    NSString *color1 = @"#f00";
    _label1.backgroundColor = [Utils colorFromHexString:color1];
    _label1.text = color1;
    
    NSString *color2 = @"#c9a57f";
    _label2.backgroundColor = [Utils colorFromHexString:color2];
    _label2.text = color2;
    
    NSString *color3 = @"#76285d";
    _label3.backgroundColor = [Utils colorFromHexString:color3];
    _label3.text = color3;
    
    NSString *color4 = @"#880018ff";
    _label4.backgroundColor = [Utils colorFromHexString:color4];
    _label4.text = color4;
}

@end
