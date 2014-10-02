//
//  Utils.m
//  HexadecimalColorsSample
//
//  Created by Igor Khrupin on 10/2/14.
//  Copyright (c) 2014 www.hrupin.com. All rights reserved.
//

#import "Utils.h"

@implementation Utils

+ (UIColor *) colorFromHexString:(NSString *)hexString {
    NSString *cleanString = [hexString stringByReplacingOccurrencesOfString:@"#" withString:@""];
    if([cleanString length] == 3) {
        NSString *red = [cleanString substringWithRange:NSMakeRange(0, 1)];
        NSString *green = [cleanString substringWithRange:NSMakeRange(1, 1)];
        NSString *blue = [cleanString substringWithRange:NSMakeRange(2, 1)];
        cleanString = [NSString stringWithFormat:@"ff%1$@%1$@%2$@%2$@%3$@%3$@", red, green, blue];
    }else if([cleanString length] == 6) {
        cleanString = [@"ff" stringByAppendingString:cleanString];
    }else{
        //do nothing
    }
    
    NSLog(@"%@", cleanString);
    
    unsigned int rgba;
    [[NSScanner scannerWithString:cleanString] scanHexInt:&rgba];
    
    CGFloat alpha = ((rgba & 0xFF000000) >> 24) / 255.0f;
    CGFloat red = ((rgba & 0x00FF0000) >> 16) / 255.0f;
    CGFloat green = ((rgba & 0x0000FF00) >> 8) / 255.0f;
    CGFloat blue = (rgba & 0x000000FF) / 255.0f;
    
    return [UIColor colorWithRed:red green:green blue:blue alpha:alpha];
}

@end
